package com.neoapp.crud.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        // Liberando H2 console
                        .requestMatchers("/h2-console/**").permitAll()
                        // Liberando endpoints do Swagger
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // Liberando login e cadastro de usuários
                        .requestMatchers("/auth/**", "/usuarios/**").permitAll()
                        // Qualquer outra rota precisa estar autenticada
                        .anyRequest().authenticated()
                )
                // Adiciona nosso filtro JWT
                .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        // Permite o H2 console funcionar dentro de um iframe
        http.headers().frameOptions().disable();

        return http.build();
    }
}
