package com.example.productservice.Clients.fakestore.fakeStoreDTO;

import com.example.productservice.Clients.fakestore.IClientProductDTO;
import com.example.productservice.DTOs.RatingDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class FakeStoreDTO implements IClientProductDTO, Serializable {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    private RatingDTO rating;
}
