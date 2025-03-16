package com.msa.fiveio.hub.infrastructure.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.List;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@OpenAPIDefinition(
    info = @Info(title = "API Document", description = "HUB SERVICE 명세서", version = "v3")
)
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .addServersItem(new Server().url("http://localhost:19090/hub-service"));
    }

 /*   @Bean
    public OpenAPI customOpenApi(@Value("${openapi.service.url}") String url) {
        log.info("serverUrl={}", url);
        return new OpenAPI()
            .servers(List.of(new Server().url(url)))
            .components(new Components().addSecuritySchemes("Bearer",
                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
            .addSecurityItem(new SecurityRequirement().addList("Bearer"))
            .info(new io.swagger.v3.oas.models.info.Info().title("KEA Project-SemiColon")
                .description("Board 관련 API")
                .version("v0 0.1"));
    }*/
}