package com.example.productservice_proxy_assignment.Inheritance.MappedSuperClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "msc_TA")
public class TA extends User {
    private String gradYear;
}
