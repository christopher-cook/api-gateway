package com.example.apigateway.bean;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

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

    @Test
    public void getEmail_Success(){
      assertEquals("test@bean.com", userBean.getEmail());
    }

    @Test
    public void setEmail_Success() {
        assertNotEquals("updated@bean.com", userBean.getEmail());
        userBean.setEmail("updated@bean.com");
        assertEquals("updated@bean.com", userBean.getEmail());
    }

    @Test
    public void getUsername_Success(){
        assertEquals("chris", userBean.getUsername());
    }


    @Test
    public void setUsername_Success() {
        assertNotEquals("Fred", userBean.getUsername());
        userBean.setUsername("Fred");
        assertEquals("Fred", userBean.getUsername());
    }

    @Test
    public void getPassword_Success() {
        assertEquals("testPass", userBean.getPassword());
    }

    @Test
    public void setPassword_Success() {
        assertNotEquals("newPass", userBean.getPassword());
        userBean.setPassword("newPass");
        assertEquals("newPass", userBean.getPassword());
    }
}
