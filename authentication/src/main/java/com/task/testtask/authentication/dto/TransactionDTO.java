package com.task.testtask.authentication.dto;

import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
public class TransactionDTO {
    Integer id;

    Instant createdAt;

    String type;

    String actor;

    Map<String, String> data;
}
