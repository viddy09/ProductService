package com.example.productservice_proxy_assignment.Controllers;

import com.example.productservice_proxy_assignment.DTOs.ProductDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController implements iProductController {
    @Override
    @GetMapping("")
    public String getAllProducts(){
        return "Returning all products";
    }

    @Override
    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable("id") Long productId) {
        return "Returning Single Product";
    }

    @Override
    @PostMapping()
    public String addNewProduct(@RequestBody ProductDTO productDTO){
        return "New Product is added"+productDTO;
    }

    @Override
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long productId){
        return "Product has been removed";
    }

}
