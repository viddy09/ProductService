package com.example.productservice_proxy_assignment.Controllers;

import com.example.productservice_proxy_assignment.DTOs.ProductDTO;
import com.example.productservice_proxy_assignment.Models.Product;
import com.example.productservice_proxy_assignment.Services.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController{

    private ProductService productService;
    ProductController(){
        this.productService = new ProductService();
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(){
        ResponseEntity<List<Product>> responseEntity;
        try{
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token", "heyaccess");
            responseEntity = new ResponseEntity<>(this.productService.getAllProducts(), headers, HttpStatus.OK);
            return responseEntity;
        }
        catch(Exception exception) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId) {
        ResponseEntity<Product> responseEntity;
        try{
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token", "heyaccess");
            responseEntity = new ResponseEntity<>(this.productService.getSingleProduct(productId), headers, HttpStatus.OK);
            return responseEntity;
        }
        catch (Exception exception){
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDTO productDTO){
        try{
            return new ResponseEntity<>(this.productService.addNewProduct(productDTO), HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") Long productId){
        boolean result = this.productService.deleteProduct(productId);
        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
