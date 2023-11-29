package dev.naman.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/products").hasAuthority("mentor")
                        .requestMatchers("/products/{id}").permitAll()
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(
                        jwtConfigurer -> {
                            jwtConfigurer.jwtAuthenticationConverter(new CustomJwtAuthenticationConverter());
                        }
                ))
                .cors().disable()
                .csrf().disable()
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
        ;

        return http.build();
    }
}
