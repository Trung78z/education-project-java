package com.hcmuss.__admin.models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {

    private Integer id;
    private String name;
    private double price;
    private Integer quantity;
    private String image;
    private String description;
    private String odometer;
    private String gearshift;
    private String type;

    private float discount;
    private ProductBrand productBrand;


    public ProductResponse() {
    }

    public ProductResponse(String name, double price, Integer quantity, String image, String description, String odometer,
                           String gearshift, String type, float discount) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.odometer = odometer;
        this.gearshift = gearshift;
        this.type = type;
        this.discount = discount;
    }


    public String getProductBrand() {
        return productBrand.getName();
    }

    public void setProductBrand(ProductBrand productBrand) {
        this.productBrand = productBrand;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    public String getGearshift() {
        return gearshift;
    }

    public void setGearshift(String gearshift) {
        this.gearshift = gearshift;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }


}
