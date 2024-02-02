package com.example.productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductDTO implements Serializable {
    private String id;
    private String title;
    private double price;
    private String description;
    private String imageURL;
    private String category;
}
