package com.example.IdentityReconciliation.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@Slf4j
public class SwaggerConfiguration {

    public SwaggerConfiguration() {
    }

    @Bean
    public GroupedOpenApi openApi(){
        return GroupedOpenApi.builder()
                .group("identity-reconciliation")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI getApiInfo() {
        try {
            OpenAPI openAPI=new OpenAPI();
            Properties properties = new Properties();
            String title = properties.getProperty("application.api.title");
            String description = properties.getProperty("application.api.description");
            openAPI.setInfo(new Info().title(title).description(description).license(new License())
                    .contact(new Contact().url("identity-reconciliation").name("dev"))
                    .version("1"));
            return openAPI;
        } catch (Exception var7) {
            log.warn("Failed to load swagger.properties", var7);
            return new OpenAPI();
        }
    }
}