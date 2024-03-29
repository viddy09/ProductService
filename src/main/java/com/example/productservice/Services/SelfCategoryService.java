package com.example.productservice.Services;

import com.example.productservice.DTOs.CategoryDTO;
import com.example.productservice.Models.Category;
import com.example.productservice.Repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfCategoryService implements ICategoryService{
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public Category getCategory(Long Id) {
        Optional<Category> category = categoryRepo.findById(Id);
        return category.get();
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Category patchCategory(Long Id, CategoryDTO categoryDTO) {
        Category category = this.getCategory(categoryDTO);
        category.setId(Id);
        return categoryRepo.save(category);
    }

    private Category getCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        //category.setProducts(categoryDTO.getProducts());
        return category;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = this.getCategory(categoryDTO);
        return categoryRepo.save(category);
    }
}
