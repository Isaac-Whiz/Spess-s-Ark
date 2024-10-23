package com.whizstudios.spessark.security;

import com.whizstudios.spessark.jwt.JwtAuthenticationEntryPoint;
import com.whizstudios.spessark.jwt.JwtRequestFilter;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

    private final JwtAuthenticationEntryPoint entryPoint;
    private final AuthenticationProvider provider;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityFilterChainConfig(
            JwtAuthenticationEntryPoint entryPoint,
            AuthenticationProvider provider,
            JwtRequestFilter jwtRequestFilter) {
        this.entryPoint = entryPoint;
        this.provider = provider;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) ->
                        request.requestMatchers(HttpMethod.POST,
                                        "/api/v1/admin/registerAdmin",
                                        "/api/v1/teacher/registerTeacher",
                                        "/api/v1/password/generateCode/**",
                                        "/api/v1/code/password/validateCode/**",
                                        "/api/v1/auth/login",
                                        "/api/v1/subject/addSubject")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET,
                                        "/api/v1/admin/admins",
                                        "/api/v1/admin/tick",
                                        "/api/v1/subject/subjects",
                                        "/api/v1/teacher/teachers/findByEmail/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.PUT,
                                        "/api/v1/code/validate/**",
                                        "/api/v1/password/resetPassword/**"
                                        )
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(provider)
                .exceptionHandling((handler) -> handler.authenticationEntryPoint(entryPoint))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
