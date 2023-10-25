package com.example.productservice_proxy_assignment.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/Categories")
public class CategoryController implements iCategoryController {
    @Override
    @GetMapping("")
    public String getAllCategory(){
        return "Returning all Categories";
    }

    @Override
    @GetMapping("/{id}")
    public String getSingleCategory(@PathVariable("id") Long categoryId){
        return "Returning single category"+categoryId;
    }
}
