package com.example.productservice_proxy_assignment.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface iCategoryController {
    @GetMapping("")
    String getAllCategory();

    @GetMapping("/{id}")
    String getSingleCategory(@PathVariable("id") Long categoryId);
}
