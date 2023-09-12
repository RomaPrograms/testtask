package com.task.testtask.transaction.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "actor", nullable = false)
    private String actor;

    @ElementCollection
    @CollectionTable(name = "transaction_data",
            joinColumns = {@JoinColumn(name = "data_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "data_name", nullable = false)
    @Column(name = "data_value", nullable = false)
    private Map<String, String> data;
}
