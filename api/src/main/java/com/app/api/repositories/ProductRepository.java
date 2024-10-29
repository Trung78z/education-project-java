package com.app.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}