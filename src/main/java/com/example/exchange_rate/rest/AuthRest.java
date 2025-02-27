package com.example.exchange_rate.rest;

import com.example.exchange_rate.dao.data.entity.User;
import com.example.exchange_rate.impl.AuthImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthRest {

    private AuthImpl authImpl;

    public AuthRest(AuthImpl authImpl) {
        this.authImpl = authImpl;
    }

    @PostMapping("/login/user")
    public Mono<Map<String,String>> login(@RequestBody User user) {
        return authImpl.login(user.getUserName(), user.getPassword());
    }

    @PostMapping("/register/user")
    public Mono<User> registerUser(@RequestBody User user) {
        return authImpl.registerUser(user);
    }

}
