package com.example.productservice.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String name;
    private String title;
    private double price;
    private String description;
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private String imageURL;
}
