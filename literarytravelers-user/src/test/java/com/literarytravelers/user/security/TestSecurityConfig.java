package com.literarytravelers.user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("test")
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable()) // Desabilitar CORS para testes
                .csrf(csrf -> csrf.disable()) // Desabilitar CSRF para testes
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()); // Permitir todas as requisições

        return http.build();
    }
}
