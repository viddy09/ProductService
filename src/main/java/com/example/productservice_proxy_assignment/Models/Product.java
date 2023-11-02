package com.example.productservice_proxy_assignment.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String name;
    private String title;
    private double price;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private String imageURL;
}
