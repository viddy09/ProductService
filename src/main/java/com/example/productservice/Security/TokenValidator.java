package com.example.productservice.Security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


/*
* Validating Token by calling UserService validate API
* */
@Component
public class TokenValidator {
    private final RestTemplateBuilder restTemplateBuilder;
    public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    //Token Validation
    public JWTObject validateToken(String token){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ValidateTokenRequestDTO validateTokenRequestDTO = new ValidateTokenRequestDTO(token);
//        System.out.println("In Token Validator===>");
        ResponseEntity<JWTObject> responseEntity = null;
        try {
            responseEntity = restTemplate.postForEntity("http://localhost:9000/auth/validate", validateTokenRequestDTO, JWTObject.class);
        }
        catch (Exception e){
//            System.out.println("Caught Exception=====>"+e.getMessage());
        }
        JWTObject jwtObject = responseEntity.getBody();
//        System.out.println("JWTOBject=======>"+jwtObject.getTokenStatus() + "  /Message   " + jwtObject.getMessage());
        if(jwtObject.getTokenStatus().equals("Valid")){
            return jwtObject;
        }
        return null;
    }
}
