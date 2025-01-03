package com.api.backend.models.news;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "news")
@Component
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class New {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Column(nullable = false)
    private String title;
    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String image;
    @NotNull
    @Column(nullable = false)
    private String content;
    private Boolean publicNew = false;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "new_category_id", nullable = false)
    @JsonBackReference
    private NewCategory newCategory;

    public New() {
    }

    public New(String title) {
        this.title = title;
    }

    public New(Integer id, String title, String description, String image, String content, Boolean publicNew,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.content = content;
        this.publicNew = publicNew;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public New(Integer id, String title, String description, String image, String content, Boolean publicNew,
            LocalDateTime createdAt, LocalDateTime updatedAt, NewCategory newCategory) {
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
        return "New [id=" + id + ", title=" + title + ", description=" + description + ", image=" + image + ", content="
                + content + ", publicNew=" + publicNew + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt
                + ", newCategory=" + newCategory + "]";
    }

}
