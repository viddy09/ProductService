package com.example.productservice.Services;

import com.example.productservice.Models.ElasticProduct;
import com.example.productservice.Models.Product;
import com.example.productservice.Models.SortParam;
import com.example.productservice.Repositories.ElasticSearchRepo;
import com.example.productservice.Repositories.ProductRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchService {
    private final ProductRepo productRepo;
    private final ElasticSearchRepo elasticSearchRepo;
    public SearchService(ProductRepo productRepo, ElasticSearchRepo elasticSearchRepo){
        this.productRepo = productRepo;
        this.elasticSearchRepo = elasticSearchRepo;
    }

    public List<Product> search(String title, int pageSize, int pageNumber, List<SortParam> sortParamList){
        Sort sort;
        if(sortParamList.get(0).getSortType().equals("ASC")){
            sort = Sort.by(sortParamList.get(0).getParaName());
        }
        else{
            sort = Sort.by(sortParamList.get(0).getParaName()).descending();
        }

        // To add multiple sorting Parameters
        for (int  i=1; i<sortParamList.size(); i++) {
            if (sortParamList.get(i).getSortType().equals("ASC")) {
                sort = sort.and(Sort.by(sortParamList.get(i).getParaName()));
            } else {
                sort = sort.and(Sort.by(sortParamList.get(i).getParaName()).descending());
            }
        }
        return productRepo.findByTitleEquals(title, PageRequest.of(pageNumber, pageSize, sort));
    }

    public List<ElasticProduct> getAllProductsByTitle(String title){
        return elasticSearchRepo.findAllByTitleContaining(title);
    }
}
