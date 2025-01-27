package com.courtside.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow all origins during development
        config.addAllowedOrigin("http://localhost:3001");
        config.addAllowedOrigin("http://localhost:3000");
        // For production

        config.addAllowedMethod("*"); // Allows all HTTP methods
        config.addAllowedHeader("*"); // Allows all headers
        config.setAllowCredentials(true); // Allows cookies and authentication headers

        source.registerCorsConfiguration("/ws/**", config);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}