package com.example.productservice.Repositories;

/*import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;*/
import org.springframework.stereotype.Repository;

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
