package com.app.server.controller;

import com.app.server.model.Product;
import com.app.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/{categoryId}/products")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return categoryService.getProductsByCategory(categoryId);
    }
}