package com.example.productservice_proxy_assignment.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;
    private String title;
    private double price;
    private String description;
    private Category category;
    private String imageURL;
}
