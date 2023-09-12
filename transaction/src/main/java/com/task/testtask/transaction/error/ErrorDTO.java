package com.task.testtask.transaction.error;

import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Value
public class ErrorDTO {
    Instant timestamp;
    int status;
    String error;
    String message;

    public ErrorDTO(HttpStatus status) {
        this(status, null);
    }

    public ErrorDTO(HttpStatus status, String message) {
        this.timestamp = Instant.now();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.status = status.value();
    }
}
