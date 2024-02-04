package com.example.productservice.Security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDTO {
    private String token;
    private String email;

    public ValidateTokenRequestDTO(String token){
        this.token = token;
    }
}
