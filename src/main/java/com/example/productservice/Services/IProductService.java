package com.example.productservice.Services;

import com.example.productservice.DTOs.ProductDTO;
import com.example.productservice.Models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);

    Product addNewProduct(ProductDTO productDTO);

    void deleteProduct(Long productId);

    Product patchProduct(ProductDTO productDto);
}
