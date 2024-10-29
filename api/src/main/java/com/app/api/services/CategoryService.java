package com.app.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.api.model.Category;
import com.app.api.model.Product;
import com.app.api.repositories.CategoryRepository;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        return category != null ? category.getProducts() : Collections.emptyList();
    }
}
