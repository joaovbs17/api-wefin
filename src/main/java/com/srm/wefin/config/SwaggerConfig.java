package com.srm.wefin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerPersonalizado(){
        return new OpenAPI()
                .info(new Info().title("API WEFIN")
                        .description("Sistema de Conversão de produtos e moedas"));
    }
}
