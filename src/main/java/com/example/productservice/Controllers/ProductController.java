package com.example.productservice.Controllers;

import com.example.productservice.DTOs.ProductDTO;
import com.example.productservice.Models.Product;
import com.example.productservice.Security.CustomJWTBased.JWTObject;
import com.example.productservice.Security.CustomJWTBased.TokenValidator;
import com.example.productservice.Services.IProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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
    private TokenValidator tokenValidator;
    public ProductController(IProductService productService, TokenValidator tokenValidator) {
        this.productService = productService;
        this.tokenValidator = tokenValidator;
    }

    //Get All Available Product
    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts(/*@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String token*/){
        ResponseEntity<List<ProductDTO>> responseEntity;
        try{
            //Commented because using Spring OAuth
            /*JWTObject authtoken = null;
            if(token !=  null) {
                authtoken = tokenValidator.validateToken(token);
            }
            if(authtoken == null){
//                System.out.println("Got token null");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token", "heyaccess");*/
            List<ProductDTO> productDTOS = this.productsToProductDTOs(this.productService.getAllProducts());
            responseEntity = new ResponseEntity<>(productDTOS, /*headers,*/ HttpStatus.OK);
            return responseEntity;
        }
        catch(Exception exception) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }

    //Get Product By Id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getSingleProduct(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                       @PathVariable("id") Long productId) {
        ResponseEntity<ProductDTO> responseEntity;
//        System.out.println("Got Token"+token);
        try{
            JWTObject authtoken = null;
            if(token !=  null) {
                authtoken = tokenValidator.validateToken(token);
            }
            if(authtoken == null){
//                System.out.println("Got token null");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token", "heyaccess");

            //To to more authorization like role based
            //responseEntity = new ResponseEntity<>(this.productService.getSingleProduct(productId, authtoken), headers, HttpStatus.OK);

            ProductDTO productDTO = this.productToProductDTO(this.productService.getSingleProduct(productId));
            responseEntity = new ResponseEntity<>(productDTO, headers, HttpStatus.OK);
            return responseEntity;
        }
        catch (Exception exception){
            System.out.println("Get Single Product======>"+exception.getMessage());
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }

    //Add New Product
    @PostMapping("")
    public ResponseEntity<ProductDTO> addNewProduct(/*@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String token,*/
                                                    @RequestBody ProductDTO productDTORequest){
        ResponseEntity<ProductDTO> responseEntity;
        try{
            /*JWTObject authtoken = null;
            if(token !=  null) {
                authtoken = tokenValidator.validateToken(token);
            }
            if(authtoken == null){
//                System.out.println("Got token null");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }*/
            ProductDTO productDTO = this.productToProductDTO(this.productService.addNewProduct(productDTORequest));
            responseEntity = new ResponseEntity<>(productDTO, HttpStatus.OK);
            return responseEntity;
        }
        catch(Exception exception){
            System.out.println("AddNewProduct Caught Exception  " + exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                              @PathVariable("id") Long productId){
        ResponseEntity<Void> responseEntity;
        try{
            JWTObject authtoken = null;
            if(token !=  null) {
                authtoken = tokenValidator.validateToken(token);
            }
            if(authtoken == null){
//                System.out.println("Got token null");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
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
    public ResponseEntity<ProductDTO> patchProduct(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                   @RequestBody ProductDTO productDTORequest) {
        ResponseEntity<ProductDTO> responseEntity;
        try{
            JWTObject authtoken = null;
            if(token !=  null) {
                authtoken = tokenValidator.validateToken(token);
            }
            if(authtoken == null){
//                System.out.println("Got token null");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
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
        if(product == null){
            return null;
        }
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
