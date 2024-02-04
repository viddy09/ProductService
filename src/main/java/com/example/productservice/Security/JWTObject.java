package com.example.productservice.Security;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class JWTObject {
    private String message;
    private String tokenStatus;
    private String email;
    private List<Role> roles = new ArrayList<>();
}
