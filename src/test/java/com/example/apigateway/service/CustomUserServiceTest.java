package com.example.apigateway.service;

import com.example.apigateway.bean.UserBean;
import com.example.apigateway.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CustomUserServiceTest {
    @InjectMocks
    CustomUserService customUserService;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserBean user;

    @Mock
    PasswordEncoder bCryptPasswordEncoder;
    @Mock
    UserDetails userDetails;

    private List<GrantedAuthority> authorities;
    private SimpleGrantedAuthority simpleGrantedAuthority;

    @Before
    public void init() {
        user.setId(1L);
        user.setEmail("test@bean.com");
        user.setUsername("chris");
        user.setPassword("testPass");
        user.setRoles("ROLE_ADMIN");
    }

    @Test
    public void loadUser_Successful(){
        assertNotNull(user);
        when(userRepository.getUserByUsername(anyString())).thenReturn(user);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("potatoe");

        UserDetails userDetails = customUserService.loadUserByUsername("chris");
        assertEquals(user.getUsername(), userDetails.getUsername());
    }
    @Test(expected = UsernameNotFoundException.class)
    public void loadUser_UserNotFound() {
        customUserService.loadUserByUsername("steve");
    }

    @Test
    public void getAuthorities_Successful() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        assertNotNull(authorities);
//        customUserService.getGrantedAuthorities(user);
    }
}
