package com.api.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.backend.models.product.ProductBrand;

public interface ProductBrandRepository extends JpaRepository<ProductBrand, Integer> {
    @SuppressWarnings("null")
    Optional<ProductBrand> findById(Integer id);
}
