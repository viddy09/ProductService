package com.example.productservice_proxy_assignment.Inheritance.MappedSuperClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "msc_Instructor")
public class Instructor extends User {
    private String company;
}
