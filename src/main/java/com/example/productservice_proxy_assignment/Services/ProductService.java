package com.example.productservice_proxy_assignment.Services;

import com.example.productservice_proxy_assignment.Clients.fakestore.client.FakeStoreClient;
import com.example.productservice_proxy_assignment.Clients.fakestore.fakeStoreDTO.FakeStoreDTO;
import com.example.productservice_proxy_assignment.DTOs.ProductDTO;
import com.example.productservice_proxy_assignment.Models.Category;
import com.example.productservice_proxy_assignment.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        //RestTemplate restTemplate = this.restTemplateBuilder.build();
        //ResponseEntity<ProductDTO[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDTO[].class);
        //ResponseEntity<ProductDTO[]> responseEntity = fakeStoreClient.
        //List<Product> products = this.getProduct(Objects.requireNonNull(responseEntity.getBody()));
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
        /*RestTemplate restTemplate = this.restTemplateBuilder.build();
            ResponseEntity<ProductDTO> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDTO.class, productId);
            List<Product> products = this.getProduct(new ProductDTO[]{responseEntity.getBody()});
            Product product = null;
            if(!products.isEmpty()) product = products.get(0);*/
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
        List<Product> products= new LinkedList<>();
        //products = this.getProduct(new FakeStoreDTO[]{fakeStoreClient.addNewProduct(fakeProductDTO)});
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

    public Product patchProduct(Long productId, Product product){
        RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreDTO fakeStoreDto = new FakeStoreDTO();
        fakeStoreDto.setDescription(product.getDescription());
        fakeStoreDto.setImage(product.getImageURL());
        fakeStoreDto.setPrice(product.getPrice());
        fakeStoreDto.setTitle(product.getTitle());
        fakeStoreDto.setCategory(product.getCategory().getName());
        List<Product> products = this.getProduct(new FakeStoreDTO[]{fakeStoreClient.patchProduct(productId,fakeStoreDto)});
        Product product1 = null;
        if(!products.isEmpty()) product1 = products.get(0);
        return product1;
    }

}
