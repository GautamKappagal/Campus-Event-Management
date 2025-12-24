package com.gautam.campus_event_management.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Campus Event Management API",
                version = "1.0",
                description = "API documentation for the Campus Event Management backend"
        )
)

public class SwaggerConfig {
}