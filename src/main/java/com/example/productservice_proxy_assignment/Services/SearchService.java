package com.example.productservice_proxy_assignment.Services;

import com.example.productservice_proxy_assignment.Models.Product;
import com.example.productservice_proxy_assignment.Models.SortParam;
import com.example.productservice_proxy_assignment.Repositories.ProductRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchService {
    private final ProductRepo productRepo;
    public SearchService(ProductRepo productRepo){
        this.productRepo = productRepo;
    }

    public List<Product> search(String title, int pageSize, int pageNumber, List<SortParam> sortParamList){
        Sort sort;
        if(sortParamList.get(0).getSortType().equals("ASC")){
            sort = Sort.by(sortParamList.get(0).getParaName());
        }
        else{
            sort = Sort.by(sortParamList.get(0).getParaName()).descending();
        }
        for (int  i=1; i<sortParamList.size(); i++) {
            if (sortParamList.get(i).getSortType().equals("ASC")) {
                sort = sort.and(Sort.by(sortParamList.get(i).getParaName()));
            } else {
                sort = sort.and(Sort.by(sortParamList.get(i).getParaName()).descending());
            }
        }
        return productRepo.findByTitleEquals(title, PageRequest.of(pageNumber, pageSize, sort));
    }
}
