package com.example.productservice_proxy_assignment.Inheritance.SingleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "sc_TA")
@DiscriminatorValue(value = "1")
public class TA extends User {
    private String gradYear;
}
