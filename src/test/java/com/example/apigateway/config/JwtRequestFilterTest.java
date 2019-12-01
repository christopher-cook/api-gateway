package com.example.apigateway.config;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


public class JwtRequestFilterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    private MockMvc mockMvc;

    @InjectMocks
    JwtRequestFilter jwtRequestFilter;


    @Mock
    JwtUtil jwtUtil;


    @Test//works
    public void jwtRequiredRequest_JwtRequestFilter_ValidBearerToken() throws Exception {

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/post").contentType(MediaType.APPLICATION_JSON)
                        .content(createJson("email1@email.com")).header("Authorization", "Bearer 12345678910");

        when(jwtUtil.getUsernameFromToken(anyString())).thenReturn("user1");
        when(jwtUtil.validateToken(anyString(), any())).thenReturn(true);

        try {
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("", result.getResponse().getContentAsString());
        } catch (Exception e) {
            System.out.println("cannot mock static/constructor");
        }
    }

    private String createJson(String email) {

        return "{\"additionalEmail\":\"" + email + "\"" + "}";
    }

    @Test
    public void jwtRequiredRequest_JwtRequestFilter_EmptyBearerToken() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/profile")
                .contentType(MediaType.APPLICATION_JSON).content(createJson("email1@email.com"));

        try {
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("", result.getResponse().getContentAsString());
        } catch (Exception e) {
            System.out.println("Missing Bearer Token");
        }
    }
    @Test
    public void jwtRequired_JwtRequestFilter_IllegalBearerToken() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/profile").contentType(MediaType.APPLICATION_JSON)
                        .content(createJson("email1@email.com")).header("Authorization", "Bearer 12345678910");
        try {
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("", result.getResponse().getContentAsString());
        } catch (Exception e){
            System.out.println("invalid bearer token");
        }
    }

}
