package com.example.apigateway.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import springfox.documentation.swagger.web.SwaggerResource;

import static org.junit.Assert.assertEquals;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DocumentationControllerTest {

    @InjectMocks
    DocumentationController documentationController;

    @Test
    public void doGet_Success() {
        List <SwaggerResource> docs = documentationController.get();

        assertEquals("user-api", docs.get(0).getName());
    }

}
