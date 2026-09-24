package com.example.exemplojpa.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // todos os endpoints
                        .allowedOrigins("*") // todas as origens
                        .allowedMethods("*") // todos os métodos HTTP
                        .allowedHeaders("*") // todos os cabeçalhos
                        .allowCredentials(false); // sem cookies
            }
        };
    }
}