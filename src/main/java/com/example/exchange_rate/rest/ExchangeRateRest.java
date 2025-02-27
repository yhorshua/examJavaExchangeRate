package com.example.exchange_rate.rest;

import com.example.exchange_rate.dao.data.entity.ExchangeRate;
import com.example.exchange_rate.dao.data.entity.Transaction;
import com.example.exchange_rate.impl.ExchangeRateImpl;
import com.example.exchange_rate.security.JwUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("exchange")
public class ExchangeRateRest {

    private ExchangeRateImpl exchangeRateImpl;
    private JwUtil jwUtil;

    public ExchangeRateRest(ExchangeRateImpl exchangeRateImpl, JwUtil jwUtil) {
        this.exchangeRateImpl = exchangeRateImpl;
        this.jwUtil = jwUtil;
    }

    @PostMapping("/save/change")
    public Mono<ExchangeRate> saveExchangeRate(@RequestBody ExchangeRate exchangeRate,
                                               @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || ! authHeader.startsWith("Bearer")) {
            return Mono.error(new RuntimeException("Token invalido"));
        }
        String token = authHeader.substring(7);
        if (! jwUtil.validateToken(token)) {
            return Mono.error(new RuntimeException("Token invalido, acceso denegado"));
        }
        return exchangeRateImpl.saveExchangeRate(exchangeRate);
    }

    @PostMapping("/convert")
    public Mono<Transaction> convert(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> request) {
        String userName = JwUtil.extractUserName(token);
        BigDecimal amount = new BigDecimal(request.get("amount"));
        String sourceCurrency = request.get("sourceCurrency");
        String targetCurrency = request.get("targetCurrency");
        return exchangeRateImpl.performChange(userName, amount, sourceCurrency, targetCurrency);
    }

}
