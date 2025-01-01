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
import com.api.backend.utils.ResponseWrapper;

@Controller
@RequestMapping("api/v1/new")
public class NewController {

    final private NewService newService;

    public NewController(NewService newService) {
        this.newService = newService;
    }

    @GetMapping()
    public ResponseEntity<ResponseWrapper<List<NewsDTO>>> getNews() {
        try {
            List<New> news = newService.getNews();
            List<NewsDTO> newsDTO = news.stream().map(NewsDTO::new).collect(Collectors.toList());

            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, newsDTO));
        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<New>> getNewById(@PathVariable Integer id) {
        try {
            New news = newService.getNewById(id);

            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, news));
        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));
        }
    }

    @PostMapping()
    public ResponseEntity<ResponseWrapper<New>> postNews(@RequestBody NewsDTO newsDTO) {
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

            return ResponseEntity.ok().body(new ResponseWrapper<>(true, 200, response));

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));
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
    public ResponseEntity<ResponseWrapper<String>> deleteNews(@PathVariable String id) {

        try {
            newService.deleteById(Integer.parseInt(id));

            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, "News deleted"));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, "Invalid ID"));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));

        }

    }
}
