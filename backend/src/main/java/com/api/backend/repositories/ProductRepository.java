package com.api.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.backend.models.product.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findById(int id);
}