package com.example.exchange_rate.dao.data.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Table("t_exchange_rate")
public class ExchangeRate {
    @Id
    private Long id;
    private String currencySource;
    private String currencyTarget;
    private BigDecimal rate;
    private LocalDateTime createdAt;

}
