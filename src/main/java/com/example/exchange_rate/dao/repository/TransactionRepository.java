package com.example.exchange_rate.dao.repository;

import com.example.exchange_rate.dao.data.entity.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Long> {
    Flux<Transaction> findByUserName(String userName);
}
