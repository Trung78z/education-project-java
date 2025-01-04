package com.hcmuss.__admin.models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductExterior {


    private String name;


    public ProductExterior() {
    }

    public ProductExterior(String name) {

        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductExterior{" +
                "name='" + name + '\'' +
                '}';
    }
}
