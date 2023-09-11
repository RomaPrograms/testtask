package com.task.testtask.transaction.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
public class TransactionDTO {

    Integer id;

    @NotNull
    Instant createdAt;

    @NotNull
    String type;

    @NotNull
    String actor;

    Map<@NotNull String, @NotNull String> data;
}
