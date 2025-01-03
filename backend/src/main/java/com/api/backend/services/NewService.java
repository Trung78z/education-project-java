package com.api.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.backend.dto.NewsDTO;
import com.api.backend.models.news.New;
import com.api.backend.models.news.NewCategory;
import com.api.backend.repositories.NewCategoryRepository;
import com.api.backend.repositories.NewRepository;

import jakarta.transaction.Transactional;

@Service
public class NewService {
    @Autowired
    final private NewRepository newRepository;
    final private NewCategoryRepository newCategoryRepository;

    public NewService(NewRepository newRepository, NewCategoryRepository newCategoryRepository) {
        this.newRepository = newRepository;
        this.newCategoryRepository = newCategoryRepository;
    }

    public List<New> getNews() {
        return newRepository.findAll();
    }

    public New getNewById(Integer newId) {
        Optional<New> optionalNew = newRepository.findById(newId);

        System.out.println(optionalNew.get());
        if (optionalNew.isPresent()) {
            return optionalNew.get();
        } else {
            throw new RuntimeException("New with ID " + newId + " not found");
        }
    }

    public New saveNew(New news) {
        Optional<NewCategory> category = newCategoryRepository.findById(news.getNewCategory().getId());
        if (!category.isPresent()) {
            throw new RuntimeException("Category not found");
        }

        Optional<New> newExist = newRepository.findByTitleAndNewCategoryId(news.getTitle(),
                news.getNewCategory().getId());
        if (newExist.isPresent()) {
            throw new RuntimeException("New already exist");
        }

        news.setNewCategory(category.get());
        return newRepository.save(news);
    }

    public New updateNew(Integer newId, NewsDTO newsDTO) {
        Optional<New> optionalNew = newRepository.findById(newId);
        if (optionalNew.isPresent()) {
            New existingNew = optionalNew.get();

            if (newsDTO.getContent() != null) {
                existingNew.setContent(newsDTO.getContent());
            }
            if (newsDTO.getDescription() != null) {
                existingNew.setDescription(newsDTO.getDescription());
            }
            if (newsDTO.getImage() != null) {
                existingNew.setImage(newsDTO.getImage());
            }
            if (newsDTO.getTitle() != null) {
                existingNew.setTitle(newsDTO.getTitle());
            }

            if (newsDTO.getNewCategory() != null) {
                NewCategory newCategory = new NewCategory();
                newCategory.setId(newsDTO.getNewCategory().getId());
                newCategory.setName(newsDTO.getNewCategory().getName());
                existingNew.setNewCategory(newCategory);
            }

            return newRepository.save(existingNew);
        } else {
            throw new RuntimeException("New with ID " + newId + " not found");
        }
    }

    @Transactional
    public void deleteById(Integer newId) {
        Optional<New> exitsOptional = newRepository.findById(newId);
        if (exitsOptional.isPresent()) {
            newRepository.deleteById(exitsOptional.get().getId());
        } else {
            throw new RuntimeException("New with ID not found");
        }
    }

}
