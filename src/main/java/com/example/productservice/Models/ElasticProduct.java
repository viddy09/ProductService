package com.example.productservice.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Getter
@Setter
@Data
@Document(indexName = "productCatalog", createIndex = true, refreshInterval = "-1", type="ElasticProduct")
public class ElasticProduct {
    @Id
    private String Id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Double)
    private double price;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Nested)
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @Field(type = FieldType.Text)
    private String imageURL;

    @Field(type = FieldType.Date)
    private Date CreatedDate;

    @Field(type = FieldType.Date)
    private Date LastModifiedDate;

    @Field(type = FieldType.Boolean)
    private boolean isDeleted;
}
