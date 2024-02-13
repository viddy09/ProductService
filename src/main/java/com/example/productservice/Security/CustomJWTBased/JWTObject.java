package com.example.productservice.Security.CustomJWTBased;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JWTObject {
    private String message;
    private String tokenStatus;
    private String email;
    private List<Role> roles = new ArrayList<>();
}
