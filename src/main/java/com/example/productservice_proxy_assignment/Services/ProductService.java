package com.example.productservice_proxy_assignment.Services;

import com.example.productservice_proxy_assignment.DTOs.ProductDTO;
import com.example.productservice_proxy_assignment.Models.Category;
import com.example.productservice_proxy_assignment.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;
    @Override
    public List<Product> getAllProducts(){
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<ProductDTO[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDTO[].class);
        List<Product> products = this.getProduct(Objects.requireNonNull(responseEntity.getBody()));
        return products;
    }
    public List<Product> getProduct(ProductDTO[] productDTOS){
        List<Product> products = new LinkedList<>();
        for(ProductDTO productDTO : productDTOS){
            Product product = new Product();
            product.setId(productDTO.getId());
            product.setTitle(productDTO.getTitle());
            Category category = new Category();
            category.setName(productDTO.getCategory());
            product.setCategory(category);
            product.setDescription(productDTO.getDescription());
            products.add(product);
        }
        return products;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<ProductDTO> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDTO.class, productId);
        List<Product> products = this.getProduct(new ProductDTO[]{responseEntity.getBody()});
        Product product = null;
        if(!products.isEmpty()) product = products.get(0);
        return product;

    }

    @Override
    public Product addNewProduct(ProductDTO productDTO){
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<ProductDTO> responseEntity = restTemplate.postForEntity("https://fakestoreapi.com/products", productDTO, ProductDTO.class);
        List<Product> products = this.getProduct(new ProductDTO[]{responseEntity.getBody()});
        Product product = null;
        if(!products.isEmpty()) product = products.get(0);
        return product;
    }

    @Override
    public boolean deleteProduct(Long productId){
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{id}", productId, ProductDTO.class);
        if(this.getSingleProduct(productId) == null){
            return true;
        }
        return false;
    }
}
