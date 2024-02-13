package com.example.productservice.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {

        //Provide Access to all request
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();

        //Provide Access to all end point expect /products GET Request
        /*http
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/products").hasAuthority("admin")
                                .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults());
                .cors().disable()
                .csrf().disable();*/

        return http.build();

    }
}
