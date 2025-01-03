package com.hcmuss.__admin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class News {
    private Integer id;
    private String title;
    private String description;
    private String image;
    private String content;
    private Boolean publicNew = false;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private NewCategory newCategory;

    public News() {
    }

    public News(Integer id, String title, String description, String image, String content, Boolean publicNew, LocalDateTime createdAt, LocalDateTime updatedAt, NewCategory newCategory) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.content = content;
        this.publicNew = publicNew;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.newCategory = newCategory;
    }

    public News(Integer id, String title, String description, String image, String content, Boolean publicNew, LocalDateTime createdAt, LocalDateTime updatedAt, Integer newCategory) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.content = content;
        this.publicNew = publicNew;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.newCategory = new NewCategory(newCategory);
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public NewCategory getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(NewCategory newCategory) {
        this.newCategory = newCategory;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", content='" + content + '\'' +
                ", publicNew=" + publicNew +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", newCategory=" + newCategory +
                '}';
    }
}
