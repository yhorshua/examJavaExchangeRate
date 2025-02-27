package com.example.exchange_rate.dao.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("t_transaction")
public class Transaction {
    @Id
    private Long id;
    private String userName;
    private BigDecimal amount;
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal convertedAmount;
    private BigDecimal rate;
    private LocalDateTime transactionDate;
}
