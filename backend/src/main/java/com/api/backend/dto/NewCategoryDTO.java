package com.api.backend.dto;

import com.api.backend.models.news.NewCategory;

public class NewCategoryDTO {
    private Integer id;
    private String name;

    public NewCategoryDTO() {
    }

    public NewCategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public NewCategoryDTO(NewCategory newCategory) {
        this.id = newCategory.getId();
        this.name = newCategory.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
