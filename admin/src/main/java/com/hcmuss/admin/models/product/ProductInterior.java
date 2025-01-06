package com.hcmuss.admin.models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInterior {

    private String name;

    public ProductInterior() {
    }

    public ProductInterior(String name) {

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
        return "ProductInterior{" +
                "name='" + name + '\'' +
                '}';
    }
}
