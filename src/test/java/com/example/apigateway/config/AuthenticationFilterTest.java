package com.example.apigateway.config;

import com.example.apigateway.bean.UserBean;
import com.example.apigateway.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationFilterTest {

    @InjectMocks
    AuthenticationFilter authenticationFilter;

    @Mock
    UserRepository userRepository;
//    @Mock
//    SecurityContextHolder securityContextHolder;
    @Mock
    SecurityContext ctx;
    @Mock
    Authentication authentication;
    @InjectMocks
    UserBean user;

    @Before
    public void init(){
        SecurityContextHolder.setContext(ctx);
    }

    @Test
    public void getType_Success() {
        String returned = authenticationFilter.filterType();
        assertEquals("pre", returned);
    }

    @Test
    public void getOrder_Success() {
        int order = authenticationFilter.filterOrder();
        assertEquals(1, order);
    }

    @Test
    public void doesFilter_Success() {
        when(ctx.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("chris");
        boolean value = authenticationFilter.shouldFilter();

        assertEquals(true, value);
    }

   @Test
    public void doRun_Success() {
        user.setUsername("chris");
        user.setEmail("test@email.com");
        user.setPassword("testPass");
        user.setId(1L);

        when(ctx.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("chris");
        when(userRepository.getUserByUsername(anyString())).thenReturn(user);

        authenticationFilter.run();
   }


}
