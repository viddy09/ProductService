package com.example.productservice.Repositories;

import com.example.productservice.Models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    @Override
    Product save(Product product);
    @Override
    Optional<Product> findById(Long aLong);
    //@Override
    //void deleteById(Long Id);
    @Override
    List<Product> findAll();

    List<Product> findByTitleEquals(String title, Pageable pageable);
}
