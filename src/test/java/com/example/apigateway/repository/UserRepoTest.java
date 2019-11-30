package com.example.apigateway.repository;


import com.example.apigateway.bean.UserBean;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;


public class UserRepoTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserBean user;

    @Mock
    JdbcTemplate jdbcTemplate;

    public void getUser_Username_ReturnObject() {
        when(jdbcTemplate.queryForObject
    }
}
