package com.example.productservice_proxy_assignment.Controllers;

import com.example.productservice_proxy_assignment.DTOs.ProductDTO;
import org.springframework.web.bind.annotation.*;

public interface iProductController {
    @GetMapping("")
    String getAllProducts();

    @GetMapping("/{id}")
    String getSingleProduct(@PathVariable("id") Long productId);

    @PostMapping()
    String addNewProduct(@RequestBody ProductDTO productDTO);

    @DeleteMapping("/{id}")
    String deleteProduct(@PathVariable("id") Long productId);
}
