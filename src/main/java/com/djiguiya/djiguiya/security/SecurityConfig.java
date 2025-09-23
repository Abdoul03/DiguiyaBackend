package com.djiguiya.djiguiya.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                auth ->
                        auth.requestMatchers(POST,"/inscription").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/parent").hasRole("PARENT")
                                .requestMatchers("/association").hasRole("ASSOCIATION")
                                .requestMatchers("/parrain").hasRole("PARRAIN")
                                .anyRequest().authenticated()
        ).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
