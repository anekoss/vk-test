package com.vk.redirector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

    @Bean
    @SuppressWarnings("removal")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(customizer -> customizer.anyRequest().authenticated())
                .httpBasic()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(401))
                .and()
                .build();
    }


}