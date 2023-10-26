package com.example.productservice_proxy_assignment.DTOs;

import com.example.productservice_proxy_assignment.Models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String id;
    private String title;
    private double price;
    private String description;
    private String imageURL;
    private String category;
}
