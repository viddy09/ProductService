package com.example.productservice_proxy_assignment.Services;

import com.example.productservice_proxy_assignment.DTOs.CategoryDTO;
import com.example.productservice_proxy_assignment.Models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Category getCategory(Long Id);
    List<Category> getAllCategory();
    Category updateCategory(Long Id, CategoryDTO categoryDTO);
    Category patchCategory(Long Id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    Category addCategory(CategoryDTO categoryDTO);

}
