package com.example.productservice_proxy_assignment.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String title;
    private double price;
    private String description;
    private String imageURL;
}
