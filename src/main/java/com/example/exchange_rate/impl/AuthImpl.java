package com.example.exchange_rate.impl;

import com.example.exchange_rate.dao.data.entity.User;
import com.example.exchange_rate.dao.repository.UserRepository;
import com.example.exchange_rate.security.JwUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthImpl {
    private UserRepository userRepository;
    private JwUtil jwUtil;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthImpl(JwUtil jwUtil, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwUtil = jwUtil;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Mono<Map<String, String>> login(String userName, String password) {
        return userRepository.findByUserName(userName)
                .filter(user -> bCryptPasswordEncoder.matches(password, user.getPassword()))
                .map(user -> {
                    String token = jwUtil.generateToken(userName);
                    Map<String, String> response = new HashMap<>();
                    response.put("token", token);
                    return response;
                });
    }

    public Mono<User> registerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
