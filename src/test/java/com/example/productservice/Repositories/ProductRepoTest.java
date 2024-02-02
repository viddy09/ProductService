package com.example.productservice.Repositories;

import com.example.productservice.Models.Category;
import com.example.productservice.Models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class ProductRepoTest {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @Test
    void testProductSave(){
        Category category = new Category();
        category.setName("Test Category");
        category.setDescription("Testing of Save");

        Product product = new Product();
        product.setPrice(12000);
        product.setTitle("Test");
        product.setCategory(category);
        product.setDescription("Testing Product Save");

        Product product1 = productRepo.save(product);

        assertNotEquals(product1, null);
    }

    @Test
    void testProductGetById(){
        Category category = new Category();
        category.setName("Test Category");
        category.setDescription("Testing of Save");

        Product product = new Product();
        product.setPrice(12000);
        product.setTitle("Test");
        product.setCategory(category);
        product.setDescription("Testing Product Save");
        product = productRepo.save(product);

        assertNotEquals(productRepo.findById(product.getId()), null);

    }
}
