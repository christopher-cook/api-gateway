package com.example.apigateway.bean;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;


public class UserBeanTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    UserBean userBean;

    @Before
    public void init() {
        userBean.setId(1L);
        userBean.setEmail("test@bean.com");
        userBean.setUsername("chris");
        userBean.setPassword("testPass");
    }

    @Test
    public void getId_Success() {
        assertEquals(1L, (long) userBean.getId());
    }
    @Test
    public void setId_Success() {
        assertNotEquals(2L, (long)userBean.getId()); //check not already 2L
        userBean.setId(2L);                     //set it ot 2L
        assertEquals(2L, (long) userBean.getId());      //check successfully changed
    }
    
}
