package com.zenex.helpdesk.config;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeHttpRequests()
                .requestMatchers(
                        "/", "/home", "/ticket/**",
                        "/track/**", "/ai-help/**",
                        "/knowledge-base/**",
                        "/qr/**", "/css/**", "/js/**",

                        // ⭐ MOST IMPORTANT FIX
                        "/chat/send",
                        "/chat/history/**",
                        "/chat/**"
                )
                .permitAll()
                .requestMatchers("/admin/**").authenticated()
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin/dashboard", true)
                .permitAll();

        http.logout().logoutUrl("/logout").permitAll();

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
