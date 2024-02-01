package com.example.productservice_proxy_assignment.Controllers;

import com.example.productservice_proxy_assignment.DTOs.CategoryDTO;
import com.example.productservice_proxy_assignment.Models.Category;
import com.example.productservice_proxy_assignment.Services.SelfCategoryService;
import com.example.productservice_proxy_assignment.Services.SelfProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/Categories")
public class CategoryController{

    @Autowired
    private SelfCategoryService selfCategoryService;
    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategory(){
        try{
            return new ResponseEntity<>(selfCategoryService.getAllCategory(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getSingleCategory(@PathVariable("id") Long categoryId){
        try{
            return new ResponseEntity<>(selfCategoryService.getCategory(categoryId), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Category> addNewCategory(@RequestBody CategoryDTO categoryDTO){
        try{
            return new ResponseEntity<>(selfCategoryService.addCategory(categoryDTO), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long categoryId){
        try{
            selfCategoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> patchProduct(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        try{
            return new ResponseEntity<>(selfCategoryService.patchCategory(id, categoryDTO), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateProduct(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        try{
            return new ResponseEntity<>(selfCategoryService.updateCategory(id, categoryDTO), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
