package com.example.productservice.Services;

import com.example.productservice.DTOs.CategoryDTO;
import com.example.productservice.Models.Category;
import com.example.productservice.Repositories.CategoryRepo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class CategoryService {
    @Autowired
    SelfCategoryService selfCategoryService;
    @Autowired
    CategoryRepo categoryRepo;

    @Test
    @DisplayName("CheckIgnoreCase - Category Name")
    @Transactional
    void testGetSingleCategory(){
        Category category = new Category();
        category.setName("Electronics");
        category.setDescription("Works on electricity");
        category = categoryRepo.save(category);

        Category category1 = selfCategoryService.getCategory(category.getId());

        assertThat(category1, hasProperty("name", equalTo("Electronics")));

        assertThat(category1.getName(), equalToIgnoringCase("ELECTRONICS"));

    }

    @Test
    @Transactional
    void testGetALLCategory(){
        Category category1 = new Category();
        category1.setName("Electronics");
        category1.setDescription("Works on electricity");
        category1 = categoryRepo.save(category1);

        Category category2 = new Category();
        category2.setName("Clothes");
        category2.setDescription("Humans clothes");
        category2 = categoryRepo.save(category2);

        List<Category> categories = selfCategoryService.getAllCategory();
        assertThat(categories.size(), greaterThanOrEqualTo(1));
    }

}
