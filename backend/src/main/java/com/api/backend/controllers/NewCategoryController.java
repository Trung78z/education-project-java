package com.api.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.api.backend.models.news.NewCategory;
import com.api.backend.services.NewCategoryService;
import com.api.backend.utils.ResourceNotFoundException;
import com.api.backend.utils.ResponseWrapper;

@Controller
@RequestMapping("/api/v1/news-category")

public class NewCategoryController {

    final private NewCategoryService newCategoryService;

    public NewCategoryController(NewCategoryService newCategoryService) {
        this.newCategoryService = newCategoryService;

    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<NewCategory>>> getCategories() {

        List<NewCategory> category = newCategoryService.getCategories();
        return ResponseEntity.ok(new ResponseWrapper<>(true, 200, category));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ResponseWrapper<NewCategory>> getIdCategories(@PathVariable String slug) {
        try {
            NewCategory response = newCategoryService.getSlugCategory(slug);

            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, response));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));
        }
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<NewCategory>> createCategory(@RequestBody NewCategory newCategory) {
        try {
            if (newCategory.getName() == null || newCategory.getName().isEmpty()) {
                throw new RuntimeException("Category name is required");
            }

            NewCategory categoryCreated = newCategoryService.saveNewCategory(newCategory);

            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, categoryCreated));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ResponseWrapper<NewCategory>> updateCategory(
            @PathVariable Integer categoryId,
            @RequestBody NewCategory newCategory) {
        try {
            NewCategory updatedCategory = newCategoryService.updateNewCategory(categoryId, newCategory);

            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, updatedCategory));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ResponseWrapper<String>> deleteCategory(@PathVariable Integer categoryId) {
        try {
            newCategoryService.deleteNewCategory(categoryId);

            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, "Category deleted successfully"));
        } catch (ResourceNotFoundException e) {

            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));
        }
    }

}
