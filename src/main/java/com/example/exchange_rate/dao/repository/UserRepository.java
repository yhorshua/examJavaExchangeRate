package com.example.exchange_rate.dao.repository;

import com.example.exchange_rate.dao.data.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByUserName(String userName);
}
