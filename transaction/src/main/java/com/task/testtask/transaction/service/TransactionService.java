package com.task.testtask.transaction.service;

import com.task.testtask.transaction.dto.TransactionDTO;
import com.task.testtask.transaction.entity.Transaction;
import com.task.testtask.transaction.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.modelMapper = new ModelMapper();
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionDTO createTransactionDTO(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        transaction = transactionRepository.saveAndFlush(transaction);
        return modelMapper.map(transaction, TransactionDTO.class);
    }
}
