package com.example.exchange_rate.dao.data.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Getter
@Setter
@Table("t_user")
public class User {

    @Id
    private Long id;
    private String userName;
    private String password;
    private String role;
}
