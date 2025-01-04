package com.hcmuss.__admin.models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductBrand {
    private Integer id;
    private String name;

    public ProductBrand() {
    }

    public ProductBrand(Integer id, String name) {
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
        return "ProductBrand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
