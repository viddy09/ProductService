package com.example.productservice_proxy_assignment.Controllers;

import com.example.productservice_proxy_assignment.Clients.fakestore.IClientProductDTO;
import com.example.productservice_proxy_assignment.Clients.fakestore.fakeStoreDTO.FakeStoreDTO;
import com.example.productservice_proxy_assignment.DTOs.ProductDTO;
import com.example.productservice_proxy_assignment.Models.Category;
import com.example.productservice_proxy_assignment.Models.Product;
import com.example.productservice_proxy_assignment.Services.IProductService;
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

    IProductService productService;
    public ProductController(IProductService productService) {
        this.productService = productService;
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

    @PostMapping("")
    public ResponseEntity<Product> addNewProduct(@RequestBody FakeStoreDTO productDTO){
        try{
            return new ResponseEntity<>(this.productService.addNewProduct(productDTO), HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") Long productId){
        this.productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public Product patchProduct(@PathVariable("productId") Long productId, @RequestBody ProductDTO productDto) {

        Product product = new Product();
        product.setId(Long.valueOf(productDto.getId()));
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        return this.productService.patchProduct(productId, product);
    }

}
