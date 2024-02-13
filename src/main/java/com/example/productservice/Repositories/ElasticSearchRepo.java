package com.example.productservice.Repositories;

import com.example.productservice.Models.ElasticProduct;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElasticSearchRepo extends ElasticsearchRepository<ElasticProduct, String> {

    List<ElasticProduct> findAllByTitleEquals(String title);
    @Query("{\n" +
            "  \"query\": {\n" +
            "    \"match\": {\n" +
            "      \"FIELD\": \"TEXT\"\n" +
            "    }\n" +
            "  }\n" +
            "}")
    List<ElasticProduct> findAllByTitleContaining(String query);

    void save(ElasticProduct elasticSearchProduct);

    /*ElasticProduct save(ElasticProduct elasticProduct);*/

}
