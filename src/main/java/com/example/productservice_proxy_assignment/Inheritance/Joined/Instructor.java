package com.example.productservice_proxy_assignment.Inheritance.Joined;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_Instructor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User {
    private String company;
}
