package com.example.productservice.Clients.fakestore.client;

import com.example.productservice.Clients.fakestore.IClientProductDTO;
import com.example.productservice.Clients.fakestore.fakeStoreDTO.FakeStoreDTO;
import com.example.productservice.DTOs.ProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class FakeStoreClient {
    private final RestTemplateBuilder restTemplateBuilder;
    FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
    public FakeStoreDTO getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO> responseEntity =restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreDTO.class, productId);
        return responseEntity.getBody();
    }

    public FakeStoreDTO[]  getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreDTO[].class);
        FakeStoreDTO[] products = Objects.requireNonNull(responseEntity.getBody());
        return products;
    }

    public FakeStoreDTO addNewProduct(FakeStoreDTO fakeStoreProductDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO> responseEntity = restTemplate.postForEntity("https://fakestoreapi.com/products",fakeStoreProductDTO, FakeStoreDTO.class);
        return responseEntity.getBody();
    }

    public void deleteProduct(Long productId){
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{id}", productId, ProductDTO.class);
    }

    public FakeStoreDTO patchProduct(Long productId, IClientProductDTO fakeStoreProductDTO){
        FakeStoreDTO fakeStoreDTO = this.requestForEntity(HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDTO ,
                FakeStoreDTO.class,
                productId).getBody();
        return fakeStoreDTO;
    }
}
