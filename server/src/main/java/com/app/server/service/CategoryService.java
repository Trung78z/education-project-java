package com.app.server.service;

import com.app.server.model.Category;
import com.app.server.model.Product;
import com.app.server.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
