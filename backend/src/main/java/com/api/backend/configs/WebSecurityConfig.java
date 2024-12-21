package com.api.backend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpBasicConfigurer<HttpSecurity> httpSecurityHttpBasicConfigurer = http.csrf(csrf -> csrf.disable())
                .authorizeRequests().requestMatchers("/**").permitAll().anyRequest().authenticated().and()
                .httpBasic();
        return http.build();
    }
}
