package com.example.productservice_proxy_assignment.Repositories;

import com.example.productservice_proxy_assignment.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/*import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ElasticSearchRepo
        /*extends ElasticsearchRepository<Product, Long>*/{

    /*List<Product> findAllByTitleEquals(String title);*/
    /*@Query("{\n" +
            "  \"query\": {\n" +
            "    \"match\": {\n" +
            "      \"FIELD\": \"TEXT\"\n" +
            "    }\n" +
            "  }\n" +
            "}")
    List<Product> findAllByTitleContaining(String query);*/
}
