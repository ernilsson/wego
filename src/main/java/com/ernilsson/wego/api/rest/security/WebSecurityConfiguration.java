package com.ernilsson.wego.api.rest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {
    private static final String[] SWAGGER_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.oauth2ResourceServer().jwt();
        return security
                .authorizeHttpRequests(auth -> auth
                        .antMatchers(SWAGGER_WHITELIST).permitAll()
                        .anyRequest()
                        .authenticated())
                .csrf().disable()
                .httpBasic().disable()
                .build();
    }
}
