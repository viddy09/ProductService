package com.example.productservice_proxy_assignment.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/Categories")
public class CategoryController{
    @GetMapping("")
    public String getAllCategory(){
        return "Returning all Categories";
    }

    @GetMapping("/{id}")
    public String getSingleCategory(@PathVariable("id") Long categoryId){
        return "Returning single category"+categoryId;
    }
}
