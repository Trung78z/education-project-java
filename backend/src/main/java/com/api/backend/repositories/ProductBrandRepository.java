package com.api.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.backend.models.product.ProductBrand;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Integer> {
    @SuppressWarnings("null")
    Optional<ProductBrand> findById(Integer id);

    ProductBrand findByName(String name);
}
