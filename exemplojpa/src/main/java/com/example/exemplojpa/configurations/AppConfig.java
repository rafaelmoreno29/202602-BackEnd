package com.example.exemplojpa.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public String nomeApp() {
        return "Exemplo JPA";
    }

    @Bean
    public Double versaoApp() {
        return 1.0;
    }

    @Bean
    public Double valorPi() {
        return 3.14159;
    }
}
