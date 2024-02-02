package com.example.productservice.Controllers;

import com.example.productservice.DTOs.ProductDTO;
import com.example.productservice.Models.Product;
import com.example.productservice.Services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/*
* Controller Providing API for getting and Updating Available Product
* */

@RestController
@RequestMapping("/products")
public class ProductController{
    private final IProductService productService;
    /*TokenValidator tokenValidator;*/
    public ProductController(IProductService productService/*, TokenValidator tokenValidator*/) {
        this.productService = productService;
        //this.tokenValidator = tokenValidator;
    }

    //Get All Available Product
    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        ResponseEntity<List<ProductDTO>> responseEntity;
        try{
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token", "heyaccess");
            List<ProductDTO> productDTOS = this.productsToProductDTOs(this.productService.getAllProducts());
            responseEntity = new ResponseEntity<>(productDTOS, headers, HttpStatus.OK);
            return responseEntity;
        }
        catch(Exception exception) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }

    //Get Product By Id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getSingleProduct(@PathVariable("id") Long productId) {
        ResponseEntity<ProductDTO> responseEntity;
        try{
            /*JWTObject authtoken = null;
            if(token !=  null) {
                authtoken = tokenValidator.validateToken(token).get();
            }*/
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token", "heyaccess");
            //responseEntity = new ResponseEntity<>(this.productService.getSingleProduct(productId, authtoken), headers, HttpStatus.OK);
            ProductDTO productDTO = this.productToProductDTO(this.productService.getSingleProduct(productId));
            responseEntity = new ResponseEntity<>(productDTO, headers, HttpStatus.OK);
            return responseEntity;
        }
        catch (Exception exception){
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }

    //Add New Product
    @PostMapping("")
    public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO productDTORequest){
        ResponseEntity<ProductDTO> responseEntity;
        try{
            ProductDTO productDTO = this.productToProductDTO(this.productService.addNewProduct(productDTORequest));
            responseEntity = new ResponseEntity<>(productDTO, HttpStatus.OK);
            return responseEntity;
        }
        catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId){
        ResponseEntity<Void> responseEntity;
        try{
            this.productService.deleteProduct(productId);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
            return responseEntity;
        }
        catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Update Product
    @PatchMapping("")
    public ResponseEntity<ProductDTO> patchProduct(@RequestBody ProductDTO productDTORequest) {
        ResponseEntity<ProductDTO> responseEntity;
        try{
            ProductDTO productDTO = this.productToProductDTO(this.productService.patchProduct(productDTORequest));
            responseEntity = new ResponseEntity<>(productDTO, HttpStatus.OK);
            return responseEntity;
        }
        catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<ProductDTO> productsToProductDTOs(List<Product> products){
        List<ProductDTO> productDTOS = new LinkedList<>();
        for(Product product : products){
            productDTOS.add(this.productToProductDTO(product));
        }
        return productDTOS;
    }

    public ProductDTO productToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(String.valueOf(product.getId()));
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageURL(product.getImageURL());
        productDTO.setDescription(productDTO.getDescription());
        if(product.getCategory() != null)
            productDTO.setCategory(product.getCategory().getName());
        return productDTO;
    }

}
