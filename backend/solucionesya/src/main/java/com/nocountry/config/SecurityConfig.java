package com.nocountry.config;

import com.nocountry.security.JwtAuthEntryPoint;
import com.nocountry.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthEntryPoint authEntryPoint;

    @Autowired
    @Bean
    public SecurityFilterChain basicFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
                .authorizeRequests(auth -> {
//                    auth.requestMatchers("/", "/error", "/webjars/**", "/swagger-ui.html").permitAll();
//                    auth.requestMatchers("/auth/**", "/users/**").permitAll();
//                    auth.requestMatchers("/login/**", "/user/**").permitAll();
                    auth.anyRequest().permitAll();
                })
//                .oauth2Login()
//                .defaultSuccessUrl("/", true).and()
                .logout(l -> l
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true).permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}