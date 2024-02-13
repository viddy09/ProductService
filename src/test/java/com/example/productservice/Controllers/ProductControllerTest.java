package com.example.productservice.Controllers;

import com.example.productservice.DTOs.ProductDTO;
import com.example.productservice.Models.Product;
import com.example.productservice.Security.CustomJWTBased.JWTObject;
import com.example.productservice.Security.CustomJWTBased.TokenValidator;
import com.example.productservice.Services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    //IProductService iProductService;
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductController productController;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    /*@Captor
    private ArgumentCaptor<String> tokenCaptor;*/

    @MockBean
    private TokenValidator tokenValidator;

    @Test
    void getAllProducts() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId((long) 69);
        product.setTitle("Test Product");
        products.add(product);

        ArrayList<ProductDTO> productDTOs = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle("Test Product");
        productDTO.setId("69");
        productDTOs.add(productDTO);

        when(productService.getAllProducts()).thenReturn(products);
        //when(productController.productsToProductDTOs(ArrayList.class)).thenReturn(new LinkedList<ProductDTO>());
        when(tokenValidator.validateToken("token")).thenReturn(new JWTObject());

        mockMvc.perform(get("/products")
                        .header(HttpHeaders.AUTHORIZATION, "token"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDTOs)));
    }

    @Test
    void test_getSingleProductException(){
        when(productService.getAllProducts()).thenThrow(new RuntimeException("Sorry something went wrong"));
        assertThrows(RuntimeException.class,()->productService.getAllProducts());
    }

    @Test
    void getSingleProduct() throws Exception {
        Product product = new Product();
        product.setId(69L);
        product.setTitle("Test Product");
        product.setPrice(9000);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle("Test Product");
        productDTO.setId("69");
        productDTO.setPrice(9000);


        when(productService.getSingleProduct(69L)).thenReturn(product);
        when(tokenValidator.validateToken("token")).thenReturn(new JWTObject());

        mockMvc.perform(get("/products/69")
                        .header(HttpHeaders.AUTHORIZATION, "token"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDTO)));
    }

    @Test
    void addNewProduct() throws Exception {
        Product product = new Product();
        product.setId(69L);
        product.setTitle("Test Product");
        product.setPrice(9000);

        ProductDTO expectedProductDTO = new ProductDTO();
        expectedProductDTO.setTitle("Test Product");
        expectedProductDTO.setPrice(9000);
        expectedProductDTO.setId("69");

        when(productService.addNewProduct(any(ProductDTO.class))).thenReturn(product);
        when(tokenValidator.validateToken("token")).thenReturn(new JWTObject());

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedProductDTO))
                        .header(HttpHeaders.AUTHORIZATION, "token"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProductDTO)));
    }

    @Test
    void deleteProduct() throws Exception {
    }

    @Test
    void patchProduct() throws Exception {
        ProductDTO expectedProductDTO = new ProductDTO();
        expectedProductDTO.setTitle("Test");
        expectedProductDTO.setPrice(12000);
        expectedProductDTO.setId(String.valueOf(69L));

        Product expectedProduct = new Product();
        expectedProduct.setPrice(12000);
        expectedProduct.setId(69L);
        expectedProduct.setTitle("Test");

        when(productService.patchProduct(any(ProductDTO.class))).thenReturn(expectedProduct);
        when(tokenValidator.validateToken("token")).thenReturn(new JWTObject());

        mockMvc.perform(patch("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedProductDTO))
                        .header(HttpHeaders.AUTHORIZATION, "token"))
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProductDTO)));

    }

    @Test
    @DisplayName("Checking Argument Authenticity")
    void test_checkCorrectArgumentPassedToGetSingleMethod() throws Exception{
        Long id=90L;
        when(productService.getSingleProduct(id)).thenCallRealMethod();
        when(tokenValidator.validateToken("token")).thenReturn(new JWTObject());
        mockMvc.perform(get("/products/90")
                .header(HttpHeaders.AUTHORIZATION, "token"));
        verify(productService).getSingleProduct(idCaptor.capture());
        verify(productService,times(1)).getSingleProduct(any());
        assertEquals (id,idCaptor.getValue());
    }
}