package com.task.testtask.authentication.service;

import com.task.testtask.authentication.dto.TransactionDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    //todo can be replaced with env variable
    private final String transactionURL = "http://localhost:8080/transactions";

    private final RestTemplate restTemplate;

    public AuthenticationService() {
        this.restTemplate = new RestTemplate();
    }

    @PostConstruct
    public void init() throws InterruptedException {
        for (int i = 0; i < 40; i++) {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setId(i);
            transactionDTO.setCreatedAt(Instant.now());
            transactionDTO.setType("type_" + i);
            transactionDTO.setActor("actor_" + i);
            Map<String, String> transactionData = new HashMap<>();
            transactionData.put("key_1_" + i, "value_1_" + i);
            transactionData.put("key_2_" + i, "value_2_" + i);
            transactionDTO.setData(transactionData);

            HttpEntity<TransactionDTO> request = new HttpEntity<>(transactionDTO);
            restTemplate.postForEntity(transactionURL, request, TransactionDTO.class);

            Thread.sleep(1000);
        }
    }
}
