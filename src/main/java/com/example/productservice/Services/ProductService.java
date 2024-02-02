package com.example.productservice.Services;

import com.example.productservice.Clients.fakestore.client.FakeStoreClient;
import com.example.productservice.Clients.fakestore.fakeStoreDTO.FakeStoreDTO;
import com.example.productservice.DTOs.ProductDTO;
import com.example.productservice.Models.Category;
import com.example.productservice.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Primary
@Service
public class ProductService implements IProductService {

    final RestTemplateBuilder restTemplateBuilder;
    final FakeStoreClient fakeStoreClient;
    final RedisTemplate<String, Object> redisTemplate;
    public ProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient,
                          RedisTemplate<String, Object> redisTemplate){
        this.restTemplateBuilder=restTemplateBuilder;
        this.fakeStoreClient=fakeStoreClient;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public List<Product> getAllProducts(){
        return this.getProduct(fakeStoreClient.getAllProducts());
    }
    public List<Product> getProduct(FakeStoreDTO[] fakeStoreDTOS){
        List<Product> products = new LinkedList<>();
        for(FakeStoreDTO productDTO : fakeStoreDTOS){
            Product product = new Product();
            product.setId(productDTO.getId());
            product.setTitle(productDTO.getTitle());
            product.setImageURL(productDTO.getImage());
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
        FakeStoreDTO fd = (FakeStoreDTO)redisTemplate.opsForHash().get("PRODUCTS", productId);
        if(fd == null){
            fd = (FakeStoreDTO)fakeStoreClient.getSingleProduct(productId);
            redisTemplate.opsForHash().put("PRODUCTS", productId, fd);
        }
        List<Product> products = this.getProduct(new FakeStoreDTO[]{fd});
        Product product = null;
        if(!products.isEmpty()) product = products.get(0);
        return product;

    }

    @Override
    public Product addNewProduct(ProductDTO productDTO){
        //RestTemplate restTemplate = this.restTemplateBuilder.build();
        //ResponseEntity<ProductDTO> responseEntity = restTemplate.postForEntity("https://fakestoreapi.com/products", productDTO, ProductDTO.class);
        //List<Product> products = this.getProduct(new ProductDTO[]{responseEntity.getBody()});
        List<Product> products = new LinkedList<>();
        products = this.getProduct(new FakeStoreDTO[]{fakeStoreClient.addNewProduct(productDTOToFakeStoreDTO(productDTO))});
        Product product = null;
        if(!products.isEmpty()) product = products.get(0);
        return product;
    }

    @Override
    public void deleteProduct(Long productId){
        //RestTemplate restTemplate = this.restTemplateBuilder.build();
        //restTemplate.delete("https://fakestoreapi.com/products/{id}", productId, ProductDTO.class);
        fakeStoreClient.deleteProduct(productId);
    }

    public Product patchProduct(ProductDTO productDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreDTO fakeStoreDto = productDTOToFakeStoreDTO(productDTO);
        List<Product> products = this.getProduct(new FakeStoreDTO[]{fakeStoreClient.patchProduct(fakeStoreDto.getId(), fakeStoreDto)});
        Product product1 = null;
        if(!products.isEmpty()) product1 = products.get(0);
        return product1;
    }

    public FakeStoreDTO productDTOToFakeStoreDTO(ProductDTO productDTO){
        FakeStoreDTO fakeStoreDTO = new FakeStoreDTO();
        fakeStoreDTO.setId(Long.valueOf(productDTO.getId()));
        fakeStoreDTO.setTitle(productDTO.getTitle());
        fakeStoreDTO.setImage(productDTO.getImageURL());
        fakeStoreDTO.setPrice(productDTO.getPrice());
        fakeStoreDTO.setDescription(productDTO.getDescription());
        return fakeStoreDTO;
    }

}
