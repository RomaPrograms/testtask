package com.task.testtask.transaction.controller;

import com.task.testtask.transaction.dto.TransactionDTO;
import com.task.testtask.transaction.dto.utils.OnCreate;
import com.task.testtask.transaction.dto.utils.OnUpdate;
import com.task.testtask.transaction.error.exceptions.NotFoundException;
import com.task.testtask.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping("/{id}")
    @Operation(summary = "Get transaction by id")
    public TransactionDTO getTransaction(@PathVariable Integer id) throws NotFoundException {
        return transactionService.getTransaction(id);
    }

    @GetMapping
    @Operation(summary = "Get all transactions by page and size")
    public Page<TransactionDTO> getAllTransactions(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "100") @Max(1000) int size) {
        return transactionService.getAllTransactions(page, size);
    }

    @PostMapping
    @Operation(summary = "Create new transaction")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody @Validated({OnCreate.class}) TransactionDTO transactionDTO) {
        TransactionDTO createdTransactionDTO = transactionService.createTransaction(transactionDTO);
        URI uri = MvcUriComponentsBuilder
                .fromMethodName(TransactionController.class,
                        "getTransaction", transactionDTO.getId())
                .build()
                .encode()
                .toUri();
        return ResponseEntity.created(uri).body(createdTransactionDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update transaction by id")
    public TransactionDTO updateTransaction(@PathVariable Integer id,
                                            @RequestBody @Validated({OnUpdate.class}) TransactionDTO transactionDTO) throws NotFoundException {
        return transactionService.updateTransactions(id, transactionDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete transaction by id")
    public void deleteTransaction(@PathVariable int id) throws NotFoundException {
        transactionService.deleteTransaction(id);
    }
}
