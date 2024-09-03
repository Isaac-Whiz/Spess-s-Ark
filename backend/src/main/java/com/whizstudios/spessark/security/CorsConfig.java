package com.whizstudios.spessark.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {


    @Value("#{'${cors.allowed-methods}'.split(',')}")
    private List<String> allowedMethods;

    @Value("#{'${cors.allowed-headers}'.split(',')}")
    private List<String> allowedHeaders;

    @Value("#{'${cors.exposed-headers}'.split(',')}")
    private List<String> exposedHeaders;

    @Value("${frontend-url}")
    private String frontendUrl;

    @Value("${admin-url}")
    private String adminUrl;

    @Value("${test-url}")
    private String testUrl;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new
                org.springframework.web.cors.CorsConfiguration();
        configuration.setAllowedHeaders(allowedHeaders);
        configuration.setAllowedOrigins(List.of(frontendUrl, adminUrl, testUrl));
        configuration.setAllowedMethods(allowedMethods);
        configuration.setExposedHeaders(exposedHeaders);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
