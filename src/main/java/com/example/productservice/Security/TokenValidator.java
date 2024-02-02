package com.example.productservice.Security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class TokenValidator {
    private final RestTemplateBuilder restTemplateBuilder;
    public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Optional<JWTObject> validateToken(String token){
        RestTemplate restTemplate = restTemplateBuilder.build();
        //restTemplate.getForEntity()
        return Optional.empty();
    }
}
