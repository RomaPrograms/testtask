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
    private int id;

    private Instant createdAt;

    private String type;

    private String actor;

    @ElementCollection
    @CollectionTable(name = "transaction_data",
            joinColumns = {@JoinColumn(name = "data_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "data_name")
    @Column(name = "data_value")
    private Map<String, String> data;
}
