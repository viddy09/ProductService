package com.example.productservice_proxy_assignment.Clients.fakestore.client;

import com.example.productservice_proxy_assignment.Clients.fakestore.IClientProductDTO;
import com.example.productservice_proxy_assignment.Clients.fakestore.fakeStoreDTO.FakeStoreDTO;
import com.example.productservice_proxy_assignment.DTOs.ProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


public class FakeStoreClient {
    private final RestTemplateBuilder restTemplateBuilder;
    FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public <T> ResponseEntity<T> requestForEntity(
           HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables));
    }
    public FakeStoreDTO getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO> responseEntity = restTemplate.getForEntity(restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreDTO.class, productId);)
        return responseEntity.getBody();
    }

    public FakeStoreDTO[]  getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO[]> responseEntity = restTemplate.getForEntity(restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreDTO[].class);)
        FakeStoreDTO[] products = Objects.requireNonNull(responseEntity.getBody());
        return products;
    }

    public FakeStoreDTO addNewProduct(IClientProductDTO fakeStoreProductDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO> responseEntity = restTemplate.getForEntity(restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreDTO.class, fakeStoreProductDTO);)
        return responseEntity.getBody();
    }

    public void deleteProduct(Long productId){
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{id}", productId, ProductDTO.class);
    }

    public FakeStoreDTO patchProduct(Long productId, IClientProductDTO fakeStoreProductDTO){
        FakeStoreDTO fakeStoreDTO = this.requestForEntity(HttpMethod.PATCH,
                "https://fakestoreapi.com/products",
                fakeStoreProductDTO ,
                FakeStoreDTO.class,
                productId).getBody();
        return fakeStoreDTO;
    }
}
