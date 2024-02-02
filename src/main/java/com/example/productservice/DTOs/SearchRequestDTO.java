package com.example.productservice.DTOs;

import com.example.productservice.Models.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDTO {
    private String query;
    private int pageNumber;
    private int pageSize;
    private List<SortParam> sortParams;
}
