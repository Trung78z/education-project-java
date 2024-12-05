package com.api.backend.dto;

import java.util.List;

public class ProductBrandDTO {
    private Integer id;
    private String name;
    private List<ProductDTO> products;

    // Constructor
    public ProductBrandDTO(Integer id, String name, List<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    // Getters and Setters
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

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
