package com.example.exchange_rate.dao.repository;

import com.example.exchange_rate.dao.data.entity.ExchangeRate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ExchangeRateRepository extends ReactiveCrudRepository<ExchangeRate, Long> {
    Mono<ExchangeRate> findByCurrencySourceAndCurrencyTarget(String source, String target);
}
