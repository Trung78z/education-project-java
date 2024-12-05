package com.api.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.backend.models.news.NewCategory;
import com.api.backend.repositories.NewCategoryRepository;
import com.api.backend.utils.ResourceNotFoundException;

@Service
public class NewCategoryService {
    final private NewCategoryRepository newCategoryRepository;

    public NewCategoryService(NewCategoryRepository newCategoryRepository) {
        this.newCategoryRepository = newCategoryRepository;
    }

    public List<NewCategory> getCategories() {
        return newCategoryRepository.findAll();
    }

    public NewCategory getSlugCategory(String slug) {
        return newCategoryRepository.findByName(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Category with slug " + slug + " not found"));
    }

    public NewCategory saveNewCategory(NewCategory newCategory) {
        Optional<NewCategory> existName = newCategoryRepository.findByName(newCategory.getName());

        if (existName.isPresent()) {
            throw new RuntimeException("Category already exists");
        } else {
            return newCategoryRepository.save(newCategory);
        }
    }

    public NewCategory updateNewCategory(Integer categoryId, NewCategory newCategory) {
        return newCategoryRepository.findById(categoryId)
                .map(category -> {
                    if (newCategory.getName() != null) {
                        category.setName(newCategory.getName());
                    }

                    return newCategoryRepository.save(category);
                })
                .orElseGet(() -> {
                    newCategory.setId(categoryId);
                    return newCategoryRepository.save(newCategory);
                });
    }

    public void deleteNewCategory(Integer categoryId) {
        Optional<NewCategory> optionalCategory = newCategoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            newCategoryRepository.deleteById(categoryId);
        } else {
            throw new ResourceNotFoundException("News category with ID " + categoryId + " not found");
        }
    }
}
