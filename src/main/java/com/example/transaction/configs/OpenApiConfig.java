package com.example.transaction.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${back.server.uri}")
    private String serverUri;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Тестовый сервис")
                        .description("Тестовый сервис для Сваггера")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Наргиза")
                                .email("nargizasaina@gmail.com")
                                )
                        ).addServersItem(server());
    }

    private Server server() {
        Server server = new Server();
        server.setUrl(serverUri);
        return server;
    }
}
