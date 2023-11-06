package com.example.productservice_proxy_assignment.DTOs;

import com.example.productservice_proxy_assignment.Models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private String Id;
    private String name;
    private List<Product> products;
    private String description;


}
