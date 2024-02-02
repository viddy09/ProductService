package com.example.productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RatingDTO implements Serializable {
    private double rate;
    private double count;
}
