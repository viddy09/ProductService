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
        /*http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
              //.formLogin(Customizer.withDefaults())
                .csrf().disable();*/
        /*http
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/products")
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .cors().disable()
                .csrf().disable();*/
        /*http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/*").permitAll()

                )
                //.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .cors().disable()
                .csrf().disable();*/
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        return http.build();

    }
}
