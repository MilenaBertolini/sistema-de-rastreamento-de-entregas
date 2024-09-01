package br.edu.iftm.rastreamento.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
            .info(new Info()
                .title("Documentação da API sobre o Sistema Rastreamento de Entregas")
                .version("Version 1 (v1)")
                .description("Esta documentação apresenta os endpoints " +
                "responsáveis pelas funcionalidades Endereco, " +
                "Pacote e Rastreamento.")
                .termsOfService("http://mytersm.com")
        
        );
    }
    
}
