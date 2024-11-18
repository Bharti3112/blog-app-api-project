package com.blogapplication.api.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog App APIs")
                        .description("Backend API for Blogging App using Java Spring Boot")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Bharti kumari")
                                .url("https://github.com/lakshygupta")
                                .email("bhartisingh2615@gmail.com"))
                        .termsOfService("Terms of Service")
                        .license(new License().name("License of APIs").url("API License URL")));
    }
}