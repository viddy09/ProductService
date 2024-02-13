package com.example.productservice.Services;

import com.example.productservice.DTOs.ProductDTO;
import com.example.productservice.Models.Category;
import com.example.productservice.Models.ElasticProduct;
import com.example.productservice.Models.Product;
import com.example.productservice.Repositories.ElasticSearchRepo;
import com.example.productservice.Repositories.ProductRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/*@Primary*/
@Service
public class SelfProductService implements IProductService{

    private final ProductRepo productRepo;
    private final ElasticSearchRepo elasticSearchRepo;
    public SelfProductService(ProductRepo productRepo, ElasticSearchRepo elasticSearchRepo){
        this.productRepo = productRepo;
        this.elasticSearchRepo = elasticSearchRepo;
    }
    @Override
    public List<Product> getAllProducts() {
//        System.out.println("In Self ProductService");
        return productRepo.findAll();
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return productRepo.findById(productId).get();
    }

    @Override
    public Product addNewProduct(ProductDTO productDTO) {
        Product product = /*this.productRepo.save(this.getProduct(productDTO))*/null;
        try{
            this.elasticSearchRepo.save(this.getElasticSearchProduct(productDTO));
        }catch (Exception e){
            System.out.println("Inside Add new Product  "+e.getMessage());
        }
//        this.elasticSearchRepo.save(this.getElasticSearchProduct(productDTO));
        return null;
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
    public Product patchProduct(ProductDTO productDTO) {
        Product product = getProduct(productDTO);
        return productRepo.save(product);
    }

    public ElasticProduct getElasticSearchProduct(ProductDTO productDTO){
        ElasticProduct elasticProduct =new ElasticProduct();
        /*elasticProduct.setId(productDTO.getId());*/
        elasticProduct.setTitle(productDTO.getTitle());
        elasticProduct.setPrice(productDTO.getPrice());
        Category category1 = new  Category();
        category1.setName(productDTO.getCategory());
        elasticProduct.setCategory(category1);
        elasticProduct.setDescription(productDTO.getDescription());
        elasticProduct.setImageURL(productDTO.getImageURL());
        return elasticProduct;
    }
}
