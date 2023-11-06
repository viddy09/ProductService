package com.example.productservice_proxy_assignment.Services;

import com.example.productservice_proxy_assignment.Clients.fakestore.IClientProductDTO;
import com.example.productservice_proxy_assignment.Clients.fakestore.fakeStoreDTO.FakeStoreDTO;
import com.example.productservice_proxy_assignment.DTOs.ProductDTO;
import com.example.productservice_proxy_assignment.Models.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);

    Product addNewProduct(ProductDTO productDTO);

    void deleteProduct(Long productId);

    Product patchProduct(Long productId, Product product);

}
