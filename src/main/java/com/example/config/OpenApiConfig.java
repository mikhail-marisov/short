package com.example.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Сокращатель ссылок",
                description = "Сокращатель ссылок", version = "1.0.0",
                contact = @Contact(
                        name = "Mikhail"
                )
        )
)
public class OpenApiConfig {

}
