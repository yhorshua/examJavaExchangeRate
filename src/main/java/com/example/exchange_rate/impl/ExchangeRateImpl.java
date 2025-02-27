package com.example.exchange_rate.impl;

import com.example.exchange_rate.dao.data.entity.ExchangeRate;
import com.example.exchange_rate.dao.data.entity.Transaction;
import com.example.exchange_rate.dao.repository.ExchangeRateRepository;
import com.example.exchange_rate.dao.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ExchangeRateImpl {
    private ExchangeRateRepository exchangeRateRepository;
    private TransactionRepository transactionRepository;

    public ExchangeRateImpl(ExchangeRateRepository exchangeRateRepository, TransactionRepository transactionRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.transactionRepository = transactionRepository;
    }

    public Mono<ExchangeRate> getExchangeRate(String source, String target) {
        return exchangeRateRepository.findByCurrencySourceAndCurrencyTarget(source, target);
    }

    public Mono<ExchangeRate> saveExchangeRate(ExchangeRate exchangeRate) {
        exchangeRate.setCreatedAt(LocalDateTime.now());
        return exchangeRateRepository.save(exchangeRate);
    }

    public Mono<Transaction> performChange(String userName, BigDecimal amount, String source, String target) {
        return getExchangeRate(source, target)
                .flatMap(rate -> {
                    BigDecimal convertedAmount = amount.multiply(rate.getRate());
                    Transaction transaction = new Transaction(null, userName, amount, source, target, convertedAmount, rate.getRate(), LocalDateTime.now());
                    return transactionRepository.save(transaction);
                });
    }
}
