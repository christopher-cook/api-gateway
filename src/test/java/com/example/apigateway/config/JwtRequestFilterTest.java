package com.example.apigateway.config;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.apigateway.service.CustomUserService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class JwtRequestFilterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    private MockMvc mockMvc;

    @InjectMocks
    JwtRequestFilter jwtRequestFilter;

    @Mock
    CustomUserService userService;

    @Mock
    JwtUtil jwtUtil;

    @Test
    public void doFilter_Failure() throws ServletException, IOException {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User("test", "test",
                true, true, true, true, new ArrayList<>());
        when(jwtUtil.getUsernameFromToken(anyString())).thenReturn("chris");
        when(userService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(jwtUtil.validateToken(anyString(), any())).thenReturn(true);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FilterChain filter = Mockito.mock(FilterChain.class);

        jwtRequestFilter.doFilterInternal(request, response, filter);
    }

    @Test
    public void doFilter_Success() throws ServletException, IOException {

        UserDetails userDetails = new org.springframework.security.core.userdetails.User("test", "test",
                true, true, true, true, new ArrayList<>());
        when(jwtUtil.getUsernameFromToken(anyString())).thenReturn("chris");
        when(userService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(jwtUtil.validateToken(anyString(), any())).thenReturn(true);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FilterChain filter = Mockito.mock(FilterChain.class);

        when(request.getHeader(anyString())).thenReturn("Bearer 9876");

        jwtRequestFilter.doFilterInternal(request, response, filter);
    }


    private String createJson(String email) {

        return "{\"additionalEmail\":\"" + email + "\"" + "}";
    }


}
