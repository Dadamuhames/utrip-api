package com.msd.utrip.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(
                new Components()
                    .addSecuritySchemes(
                        SECURITY_SCHEME_NAME,
                        new SecurityScheme()
                            .name(SECURITY_SCHEME_NAME)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            )
            .addSecurityItem(
                new SecurityRequirement().addList(SECURITY_SCHEME_NAME)
            );
    }

    @Bean
    public GroupedOpenApi clientApi() {
        return GroupedOpenApi.builder()
            .group("client-api")
            .packagesToScan("com.msd.utrip.controller.client")
            .pathsToMatch("/api/v1/client/**")
            .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
            .group("admin-api")
            .packagesToScan("com.msd.utrip.controller.admin")
            .pathsToMatch("/api/v1/admin/**")
            .build();
    }

    @Bean
    public GroupedOpenApi agencyApi() {
        return GroupedOpenApi.builder()
            .group("agency-api")
            .packagesToScan("com.msd.utrip.controller.agency")
            .pathsToMatch("/api/v1/agency/**")
            .build();
    }

    @Bean
    public GroupedOpenApi filesApi() {
        return GroupedOpenApi.builder()
            .group("files-api")
            .packagesToScan("com.msd.utrip.controller.file")
            .pathsToMatch("/api/v1/files/**")
            .build();
    }
}
