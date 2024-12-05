package com.api.backend.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.api.backend.dto.NewsDTO;
import com.api.backend.models.news.New;
import com.api.backend.models.news.NewCategory;
import com.api.backend.services.NewService;

@Controller
@RequestMapping("api/v1/new")
public class NewController {

    final private NewService newService;

    public NewController(NewService newService) {
        this.newService = newService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getNews() {
        List<New> newsList = newService.getNews();
        List<NewsDTO> newsDTOs = newsList.stream()
                .map(news -> new NewsDTO(news))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(Map.of("success", true, "message", newsDTOs));
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> postNews(@RequestBody NewsDTO newsDTO) {
        try {
            New createdNews = new New();
            createdNews.setContent(newsDTO.getContent());
            createdNews.setDescription(newsDTO.getDescription());
            createdNews.setImage(newsDTO.getImage());
            createdNews.setTitle(newsDTO.getTitle());
            createdNews.setPublicNew(newsDTO.getPublicNew());

            if (newsDTO.getNewCategory() != null) {
                NewCategory newCategory = new NewCategory();
                newCategory.setId(newsDTO.getNewCategory().getId());
                newCategory.setName(newsDTO.getNewCategory().getName());
                createdNews.setNewCategory(newCategory);
            }

            New response = newService.saveNew(createdNews);

            return ResponseEntity.ok(Map.of("message", response, "success", true));

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage(), "success", false));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "An error occurred while processing the request"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateNews(@PathVariable Integer id, @RequestBody NewsDTO newsDTO) {
        try {
            New updatedNews = newService.updateNew(id, newsDTO);

            return ResponseEntity.ok(Map.of("message", updatedNews, "success", true));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage(), "success", false));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "An error occurred while processing the request"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteNews(@PathVariable String id) {

        try {
            newService.deleteById(Integer.parseInt(id));
            return ResponseEntity.ok(Map.of("success", id));
        } catch (NumberFormatException e) {
            return ResponseEntity.status(400)
                    .body(Map.of("success", false, "message", "Id not number"));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "An error occurred while processing the request"));

        }

    }
}
