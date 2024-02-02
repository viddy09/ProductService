package com.example.productservice.Services;

import com.example.productservice.DTOs.CategoryDTO;
import com.example.productservice.Models.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategory(Long Id);
    List<Category> getAllCategory();
    Category patchCategory(Long Id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    Category addCategory(CategoryDTO categoryDTO);

}
