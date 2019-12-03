package com.example.apigateway.repository;

import com.example.apigateway.bean.UserBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

//import static org.junit.Assert.assertEquals;
import java.sql.ResultSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    @InjectMocks
    UserRepository userRepository;

    @Mock
    UserBean user;

    @Mock
    ResultSet rs;
    @Mock
    JdbcTemplate jdbc;

    @Before
    public void init() {
        user.setId(1L);
        user.setUsername("chris");
        user.setPassword("testPass");
        user.setEmail("test@email.com");
    }

    @Test
    public void getUserBean_Success() {
        ArgumentCaptor <UserBean> rowSet = ArgumentCaptor.forClass(UserBean.class);
        UserBean user = userRepository.getUserByUsername("chris");
        verify(jdbc).queryForObject(anyString(), (RowMapper<Object>) any(), rowSet.capture());


    }
}
