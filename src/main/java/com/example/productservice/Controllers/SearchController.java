package com.example.productservice_proxy_assignment.Controllers;

import com.example.productservice_proxy_assignment.DTOs.ProductDTO;
import com.example.productservice_proxy_assignment.DTOs.SearchRequestDTO;
import com.example.productservice_proxy_assignment.Models.Product;
import com.example.productservice_proxy_assignment.Services.SearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @PostMapping
    public List<ProductDTO> search(@RequestBody SearchRequestDTO searchRequestDTO){
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
}
