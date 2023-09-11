package com.task.testtask.transaction.controller;

import com.task.testtask.transaction.dto.TransactionDTO;
import com.task.testtask.transaction.service.TransactionService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@RestController
@Validated
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{transactionId}")
    public TransactionDTO getTransaction(@PathVariable Integer transactionId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {
        TransactionDTO createdTransactionDTO = transactionService.createTransactionDTO(transactionDTO);
        URI uri = MvcUriComponentsBuilder
                .fromMethodName(TransactionController.class,
                        "getTransaction", transactionDTO.getId())
                .build()
                .encode()
                .toUri();
        return ResponseEntity.created(uri).body(createdTransactionDTO);
    }
}
