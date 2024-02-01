package com.example.productservice_proxy_assignment.Services;

import com.example.productservice_proxy_assignment.DTOs.ProductDTO;
import com.example.productservice_proxy_assignment.Models.Category;
import com.example.productservice_proxy_assignment.Models.Product;
import com.example.productservice_proxy_assignment.Repositories.ElasticSearchRepo;
import com.example.productservice_proxy_assignment.Repositories.ProductRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductService implements IProductService{

    private final ProductRepo productRepo;
    /*private final ElasticSearchRepo elasticSearchRepo;*/
    public SelfProductService(ProductRepo productRepo /*ElasticSearchRepo elasticSearchRepo*/){
        this.productRepo = productRepo;
        /*this.elasticSearchRepo = elasticSearchRepo;*/
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return productRepo.findById(productId).get();
    }

    @Override
    public Product addNewProduct(ProductDTO productDTO) {
        Product product = this.getProduct(productDTO);
        Product product1 = this.productRepo.save(product);
        /*this.elasticSearchRepo.save(product);*/
        return product1;
    }

    public Product getProduct(@NotNull ProductDTO productDTO){
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImageURL());
        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepo.deleteById(productId);
    }

    @Override
    public Product patchProduct(Long productId, Product product) {
        return productRepo.save(product);
    }
}
