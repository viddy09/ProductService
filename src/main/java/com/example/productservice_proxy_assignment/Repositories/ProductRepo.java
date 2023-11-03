package com.example.productservice_proxy_assignment.Repositories;

import com.example.productservice_proxy_assignment.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    @Override
    Product save(Product product);
}
