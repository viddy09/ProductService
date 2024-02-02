package com.example.productservice.Repositories;

import com.example.productservice.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Override
    Category save(Category entity);
    @Override
    void deleteById(Long id);
    @Override
    Optional<Category> findById(Long aLong);
    @Override
    List<Category> findAll();
}
