package com.task.testtask.transaction.dto;

import com.task.testtask.transaction.dto.utils.OnCreate;
import com.task.testtask.transaction.dto.utils.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
public class TransactionDTO {

    @NotNull(groups = {OnCreate.class})
    Integer id;

    @NotNull(groups = {OnCreate.class})
    Instant createdAt;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    String type;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    String actor;

    Map<@NotBlank(groups = {OnCreate.class, OnUpdate.class}) String,
            @NotBlank(groups = {OnCreate.class, OnUpdate.class}) String> data;
}
