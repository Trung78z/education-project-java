package com.api.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.backend.models.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Optional<Product> findById(Integer id);
    Product findByName(String productName);
}