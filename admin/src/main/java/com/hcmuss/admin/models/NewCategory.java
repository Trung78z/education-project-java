package com.hcmuss.admin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewCategory {
    private Integer id;
    private String name;

    public NewCategory() {
    }

    public NewCategory(Integer id) {
        this.id = id;
    }

    public NewCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "NewCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
