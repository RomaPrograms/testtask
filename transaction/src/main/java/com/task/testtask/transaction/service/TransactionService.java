package com.task.testtask.transaction.service;

import com.task.testtask.transaction.dto.TransactionDTO;
import com.task.testtask.transaction.entity.Transaction;
import com.task.testtask.transaction.error.exceptions.NotFoundException;
import com.task.testtask.transaction.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.modelMapper = new ModelMapper();
        this.transactionRepository = transactionRepository;
    }

    @Transactional(readOnly = true)
    public TransactionDTO getTransaction(int id) throws NotFoundException {
        Transaction transaction = findTransactionById(id);
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<TransactionDTO> getAllTransactions(int page, int size) {
        Page<Transaction> transaction = transactionRepository.findAll(PageRequest.of(page, size));
        return transaction.map(value -> modelMapper.map(value, TransactionDTO.class));
    }

    @Transactional
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        transaction = transactionRepository.saveAndFlush(transaction);
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    @Transactional
    public TransactionDTO updateTransactions(int id, TransactionDTO transactionDTO) throws NotFoundException {
        findTransactionById(id);
        return createTransaction(transactionDTO);
    }

    @Transactional
    public void deleteTransaction(int id) throws NotFoundException {
        Transaction transaction = findTransactionById(id);
        transactionRepository.delete(transaction);
    }

    public Transaction findTransactionById(int id) throws NotFoundException {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction with id: " + id + " wasn't found"));
    }
}
