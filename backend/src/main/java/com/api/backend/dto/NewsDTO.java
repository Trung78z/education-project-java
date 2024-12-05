package com.api.backend.dto;

import com.api.backend.models.news.New;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NewsDTO {
    private Integer id;
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be empty")
    private String description;
    private String image;
    @NotNull(message = "Content cannot be null")
    @NotBlank(message = "Content cannot be empty")
    private String content;

    private Boolean publicNew;
    private String createdAt;
    private String updatedAt;
    private NewCategoryDTO newCategory;

    public NewCategoryDTO getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(NewCategoryDTO newCategory) {
        this.newCategory = newCategory;
    }

    public NewsDTO() {
    }

    public NewsDTO(New news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.description = news.getDescription();
        this.image = news.getImage();
        this.content = news.getContent();
        this.publicNew = news.getPublicNew();
        this.createdAt = news.getCreatedAt().toString();
        this.updatedAt = news.getUpdatedAt().toString();
        this.newCategory = new NewCategoryDTO(news.getNewCategory());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getPublicNew() {
        return publicNew;
    }

    public void setPublicNew(Boolean publicNew) {
        this.publicNew = publicNew;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
