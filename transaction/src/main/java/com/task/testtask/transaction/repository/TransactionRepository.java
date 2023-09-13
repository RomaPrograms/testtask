package com.task.testtask.transaction.repository;

import com.task.testtask.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<Transaction> findById(int id);

    //Search operations can look like those
    List<Transaction> getTransactionsByType(String name);

    List<Transaction> getTransactionsByActor(String actor);

    List<Transaction> findAllByCreatedAtBetween(Instant startTime, Instant endTime);
}
