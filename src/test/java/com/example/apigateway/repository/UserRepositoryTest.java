package com.example.apigateway.repository;

import com.example.apigateway.bean.UserBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

//import static org.junit.Assert.assertEquals;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @InjectMocks
    UserRepository userRepository;


    @Mock
    ResultSet rs;

    @Mock
    JdbcTemplate jdbc;

    @Captor
    private ArgumentCaptor<RowMapper<UserBean>> rowMapperCaptor;


    @Before
    public void init() throws SQLException {
       UserBean user = new UserBean(1L, "cgcook81@gmail.com", "chris", "testPass");

        when(rs.getLong(any())).thenReturn(user.getId());
        when(rs.getString(any())).thenReturn(user.getEmail(), user.getUsername(), user.getPassword());
    }

    @Test
    public void getUserBean_Success() throws SQLException {
        userRepository.getUserByUsername("chris");
        verify(jdbc).queryForObject(anyString(), any(), rowMapperCaptor.capture());

        RowMapper<UserBean> rm = rowMapperCaptor.getValue();
        UserBean returnedUser = rm.mapRow(rs, 1);

    }
}
