package org.example.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("MoySklad API")
                        .description("Project for Moy Sklad")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Kirill Dmitriev")
                                .email("email@example.com")
                                .url("https://example.com"))
                        .license(new License()
                                .name("License name")
                                .url("https://link-to-license.example.com")));
    }
}

