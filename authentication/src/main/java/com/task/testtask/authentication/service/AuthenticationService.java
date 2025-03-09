package com.task.testtask.authentication.service;

import com.task.testtask.authentication.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AuthenticationService {

    private final AtomicInteger counter = new AtomicInteger(-1);
    private final WebClient webClient;

    public AuthenticationService(@Value("${transaction-service.url}") String transactionURL) {
        this.webClient = WebClient.create(transactionURL);
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

            webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(transactionDTO)
                    .retrieve();

            Thread.sleep(1000);
        }
    }
}
