package com.example.productservice_proxy_assignment.Services;

import com.example.productservice_proxy_assignment.Clients.fakestore.IClientProductDTO;
import com.example.productservice_proxy_assignment.Clients.fakestore.client.FakeStoreClient;
import com.example.productservice_proxy_assignment.Clients.fakestore.fakeStoreDTO.FakeStoreDTO;
import com.example.productservice_proxy_assignment.DTOs.ProductDTO;
import com.example.productservice_proxy_assignment.Models.Category;
import com.example.productservice_proxy_assignment.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

public class ProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
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
        for(FakeStoreDTO productDTO : productDTOS){
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
        //RestTemplate restTemplate = this.restTemplateBuilder.build();
        //ResponseEntity<ProductDTO> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDTO.class, productId);
        //List<Product> products = this.getProduct(new ProductDTO[]{responseEntity.getBody()});
        //Product product = null;
        //if(!products.isEmpty()) product = products.get(0);
        List<Product> products = this.getProduct(new FakeStoreDTO[]{fakeStoreClient.getSingleProduct(productId)});
        Product product = null;
        if(!products.isEmpty()) product = products.get(0);
        return product;

    }

    @Override
    public Product addNewProduct(IClientProductDTO fakeProductDTO){
        //RestTemplate restTemplate = this.restTemplateBuilder.build();
        //ResponseEntity<ProductDTO> responseEntity = restTemplate.postForEntity("https://fakestoreapi.com/products", productDTO, ProductDTO.class);
        //List<Product> products = this.getProduct(new ProductDTO[]{responseEntity.getBody()});
        List<Product> products = this.getProduct(new FakeStoreDTO[]{fakeStoreClient.addNewProduct(fakeProductDTO)});
        Product product = null;
        if(!products.isEmpty()) product = products.get(0);
        return product;
    }

    @Override
    public boolean deleteProduct(Long productId){
        //RestTemplate restTemplate = this.restTemplateBuilder.build();
        //restTemplate.delete("https://fakestoreapi.com/products/{id}", productId, ProductDTO.class);
        fakeStoreClient.deleteProduct(productId);
        if(this.getSingleProduct(productId) == null){
            return true;
        }
        return false;
    }

    public Product patchProduct(Long productId, FakeStoreDTO fakeProductDTO){
        fakeProductDTO.setPrice(2.22);
        fakeProductDTO.setDescription("XYZ");
        List<Product> products = this.getProduct(new FakeStoreDTO[]{fakeStoreClient.patchProduct(productId,fakeProductDTO)});
        Product product = null;
        if(!products.isEmpty()) product = products.get(0);
        return product;
    }

}
