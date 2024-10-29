package com.app.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}