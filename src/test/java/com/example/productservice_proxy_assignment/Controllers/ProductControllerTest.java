package com.example.productservice_proxy_assignment.Controllers;

import com.example.productservice_proxy_assignment.DTOs.ProductDTO;
import com.example.productservice_proxy_assignment.Models.Product;
import com.example.productservice_proxy_assignment.Services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ProductController.class)
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

    @Test
    void getAllProducts() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)));
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
        product.setName("Test");
        product.setPrice(9000);

        when(productService.getSingleProduct(69L)).thenReturn(product);

        mockMvc.perform(get("/products/69"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(product)));
    }

    @Test
    void addNewProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle("Test");
        productDTO.setPrice(9000);

        Product expectedProduct = new Product();
        expectedProduct.setPrice(9000);
        expectedProduct.setId(89L);
        expectedProduct.setName("Test");

        when(productService.addNewProduct(any(ProductDTO.class))).thenReturn(expectedProduct);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)));
    }

    @Test
    void deleteProduct() throws Exception {
    }

    /*@Test
    void patchProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle("Test");
        productDTO.setPrice(9000);
        productDTO.setId(String.valueOf(69L));

        Product expectedProduct = new Product();
        expectedProduct.setPrice(12000);
        expectedProduct.setId(69L);
        expectedProduct.setName("Test");

        when(productService.patchProduct(69L,any(Product.class))).thenReturn(expectedProduct);

        *//*mockMvc.perform(patch("/products/69")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)))*//*;

    }*/

    @Test
    @DisplayName("Checking Argument Authenticity")
    void test_checkCorrectArgumentPassedToGetSingleMethod(){
        Long id=90L;
        when(productService.getSingleProduct(id)).thenCallRealMethod();
        //productController.getSingleProduct(id);
        verify(productService).getSingleProduct(idCaptor.capture());
        verify(productService,times(1)).getSingleProduct(any());
        assertEquals (id,idCaptor.getValue());
    }
}