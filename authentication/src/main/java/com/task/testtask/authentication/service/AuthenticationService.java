package com.task.testtask.authentication.service;

import com.task.testtask.authentication.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AuthenticationService {

    private final String transactionURL;
    private final AtomicInteger counter = new AtomicInteger(-1);
    private final RestClient restClient;

    public AuthenticationService(@Value("${transaction-service.url}") String transactionURL) {
        this.transactionURL = transactionURL;
        this.restClient = RestClient.create();
    }

    public void createTransactions(int size) throws InterruptedException {
        int finalValue = counter.get() + size;
        while (counter.get() < finalValue) {
            int id = counter.incrementAndGet();
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setId(id);
            transactionDTO.setCreatedAt(Instant.now());
            transactionDTO.setType("type_" + id);
            transactionDTO.setActor("actor_" + id);
            Map<String, String> transactionData = new HashMap<>();
            transactionData.put("key_1_" + id, "value_1_" + id);
            transactionData.put("key_2_" + id, "value_2_" + id);
            transactionDTO.setData(transactionData);

            restClient.post()
                    .uri(transactionURL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(transactionDTO)
                    .retrieve()
                    .toBodilessEntity();

            Thread.sleep(1000);
        }
    }
}
