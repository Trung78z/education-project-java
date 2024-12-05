package com.api.backend.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.backend.models.news.NewCategory;
import com.api.backend.services.NewCategoryService;
import com.api.backend.utils.ResourceNotFoundException;

@Controller
@RequestMapping("/api/v1/news-category")

public class NewCategoryController {

    final private NewCategoryService newCategoryService;

    public NewCategoryController(NewCategoryService newCategoryService) {
        this.newCategoryService = newCategoryService;

    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCategories() {

        List<NewCategory> category = newCategoryService.getCategories();
        return ResponseEntity.ok().body(Map.of("success", true, "message", category));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Map<String, Object>> getIdCategories(@PathVariable String slug) {
        try {
            NewCategory response = newCategoryService.getSlugCategory(slug);

            return ResponseEntity.ok().body(Map.of("success", true, "message", response));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "An error occurred while processing the request"));
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createCategory(@RequestBody NewCategory newCategory) {
        try {
            if (newCategory.getName() == null || newCategory.getName().isEmpty()) {
                throw new RuntimeException("Category name is required");
            }

            NewCategory categoryCreated = newCategoryService.saveNewCategory(newCategory);

            return ResponseEntity.ok().body(Map.of("success", true, "message", categoryCreated));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "An error occurred while processing the request"));
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String, Object>> updateCategory(
            @PathVariable Integer categoryId,
            @RequestBody NewCategory newCategory) {
        try {
            NewCategory updatedCategory = newCategoryService.updateNewCategory(categoryId, newCategory);
            return ResponseEntity.ok().body(Map.of("success", true, "message", updatedCategory));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "An error occurred while processing the request"));
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Integer categoryId) {
        try {
            newCategoryService.deleteNewCategory(categoryId);
            return ResponseEntity.ok().body(Map.of("success", true, "message", "Category deleted successfully"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "An error occurred while processing the request"));
        }
    }

}
