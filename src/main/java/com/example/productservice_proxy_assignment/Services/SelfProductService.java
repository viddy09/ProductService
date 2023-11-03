package com.example.productservice_proxy_assignment.Services;

import com.example.productservice_proxy_assignment.Clients.fakestore.fakeStoreDTO.FakeStoreDTO;
import com.example.productservice_proxy_assignment.Models.Category;
import com.example.productservice_proxy_assignment.Models.Product;
import com.example.productservice_proxy_assignment.Repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductService implements IProductService{

    ProductRepo productRepo;
    SelfProductService(ProductRepo productRepo){
        this.productRepo = productRepo;
    }
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public Product addNewProduct(FakeStoreDTO fakeProductDTO) {
        Product product = new Product();
        product.setTitle(fakeProductDTO.getTitle());
        product.setPrice(fakeProductDTO.getPrice());
        product.setDescription(fakeProductDTO.getDescription());
        product.setImageURL(fakeProductDTO.getImage());
        Category category = new Category();
        category.setName(fakeProductDTO.getCategory());
        product.setCategory(category);
        Product product1 = this.productRepo.save(product);
        return product1;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public Product patchProduct(Long productId, Product product) {
        return null;
    }
}
