package com.example.apigateway.config;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@EnableAutoConfiguration
class DocumentationController implements SwaggerResourcesProvider {
  @Override
  public List get() {
    List resources = new ArrayList<>();
    resources.add(swaggerResource("user-api", "/user/v2/api-docs", "2.0"));
    resources.add(swaggerResource("comment-api", "/comment/v2/api-docs", "2.0"));
    resources.add(swaggerResource("post-api","/post/v2/api-docs","2.0"));
    return resources;
  }
  private SwaggerResource swaggerResource(String name, String location, String version) {
    SwaggerResource swaggerResource = new SwaggerResource();
    swaggerResource.setName(name);
    swaggerResource.setLocation(location);
    swaggerResource.setSwaggerVersion(version);
    return swaggerResource;
  }
}
