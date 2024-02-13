package com.example.productservice.Controllers;

import com.example.productservice.DTOs.ProductDTO;
import com.example.productservice.DTOs.SearchRequestDTO;
import com.example.productservice.Models.ElasticProduct;
import com.example.productservice.Models.Product;
import com.example.productservice.Security.CustomJWTBased.JWTObject;
import com.example.productservice.Security.CustomJWTBased.TokenValidator;
import com.example.productservice.Services.SearchService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/*
* Search Controller used to give result based on various filters like sort, view more result, etc
* */
@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    private TokenValidator tokenValidator;

    public SearchController(SearchService searchService, TokenValidator tokenValidator){
        this.searchService = searchService;
        this.tokenValidator = tokenValidator;
    }

    @PostMapping
    public List<ProductDTO> search(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                   @RequestBody SearchRequestDTO searchRequestDTO) throws Exception{
        JWTObject authtoken = null;
        if(token !=  null) {
            authtoken = tokenValidator.validateToken(token);
        }
        if(authtoken == null){
//                System.out.println("Got token null");
            throw new Exception("UnAuthorized Request");
        }
        List<Product> products = searchService.search(searchRequestDTO.getQuery(), searchRequestDTO.getPageSize()
                , searchRequestDTO.getPageNumber()
                , searchRequestDTO.getSortParams());
        List<ProductDTO> productDTOS = new LinkedList<>();
        for(Product product: products){
            productDTOS.add(getProductDTO(product));
        }
        return productDTOS;
    }

    public static ProductDTO getProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(String.valueOf(product.getId()));
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageURL(product.getImageURL());
        return productDTO;
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<ElasticProduct>> getAllProducts(@PathVariable("title") String title) throws Exception{
        ResponseEntity<List<ElasticProduct>> responseEntity;
        try{
            List<ElasticProduct> products = searchService.getAllProductsByTitle(title);
            responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
