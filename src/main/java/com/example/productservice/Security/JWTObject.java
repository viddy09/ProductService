package com.example.productservice_proxy_assignment.Security;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JWTObject {
    private String email;
    private List<Role> roles = new ArrayList<>();
}
