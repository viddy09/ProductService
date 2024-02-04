package com.example.productservice.Controllers;

import com.example.productservice.DTOs.CategoryDTO;
import com.example.productservice.Models.Category;
import com.example.productservice.Security.JWTObject;
import com.example.productservice.Security.TokenValidator;
import com.example.productservice.Services.SelfCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/*
 * Controller Providing API for getting and Updating Available Categories
 * */
@RestController
@RequestMapping("/products/Categories")
public class CategoryController{

    @Autowired
    private SelfCategoryService selfCategoryService;

    @Autowired
    private TokenValidator tokenValidator;

    //Get All Available Category
    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        ResponseEntity<List<CategoryDTO>> responseEntity;
        try{
            JWTObject authtoken = null;
            if(token !=  null) {
                authtoken = tokenValidator.validateToken(token);
            }
            if(authtoken == null){
//                System.out.println("Got token null");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            List<CategoryDTO> categoryDTOS = this.categorysToCategoryDTOs(selfCategoryService.getAllCategory());
            responseEntity = new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
            return responseEntity;
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get Category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getSingleCategory(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable("id") Long categoryId){
        ResponseEntity<CategoryDTO> responseEntity;
        try{
            JWTObject authtoken = null;
            if(token !=  null) {
                authtoken = tokenValidator.validateToken(token);
            }
            if(authtoken == null){
//                System.out.println("Got token null");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            CategoryDTO categoryDTO = this.categoryDTOToCategoryDTO(selfCategoryService.getCategory(categoryId));
            responseEntity = new ResponseEntity<>(categoryDTO, HttpStatus.OK);
            return responseEntity;
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Add New Category
    @PostMapping(value = "")
    public ResponseEntity<CategoryDTO> addNewCategory(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody CategoryDTO categoryDTORequest){
        ResponseEntity<CategoryDTO> responseEntity;
        try{
            JWTObject authtoken = null;
            if(token !=  null) {
                authtoken = tokenValidator.validateToken(token);
            }
            if(authtoken == null){
//                System.out.println("Got token null");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            CategoryDTO categoryDTO = this.categoryDTOToCategoryDTO(selfCategoryService.addCategory(categoryDTORequest));
            responseEntity = new ResponseEntity<>(categoryDTO, HttpStatus.OK);
            return responseEntity;
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete Category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable("id") Long categoryId){
        ResponseEntity<Void> responseEntity;
        try{
            JWTObject authtoken = null;
            if(token !=  null) {
                authtoken = tokenValidator.validateToken(token);
            }
            if(authtoken == null){
//                System.out.println("Got token null");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            selfCategoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Update Category
    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDTO> patchProduct(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody CategoryDTO categoryDTORequest, @PathVariable("id") Long id){
        ResponseEntity<CategoryDTO> responseEntity;
        try{
            JWTObject authtoken = null;
            if(token !=  null) {
                authtoken = tokenValidator.validateToken(token);
            }
            if(authtoken == null){
//                System.out.println("Got token null");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            CategoryDTO categoryDTO = this.categoryDTOToCategoryDTO(selfCategoryService.patchCategory(id, categoryDTORequest));
            responseEntity = new ResponseEntity<>(categoryDTO, HttpStatus.OK);
            return responseEntity;
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<CategoryDTO> categorysToCategoryDTOs(List<Category> categories){
        List<CategoryDTO> categoryDTOs = new LinkedList<>();
        for (Category category: categories){
            categoryDTOs.add(this.categoryDTOToCategoryDTO(category));
        }
        return categoryDTOs;
    }

    public CategoryDTO categoryDTOToCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(String.valueOf(category.getId()));
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setProducts(category.getProducts());
        return categoryDTO;
    }
}
