package com.hcmuss.__admin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

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
    @JsonProperty("productBrand")
    private ProductBrand productBrand;

    public Product() {
    }

    public Product(Integer id, String name, double price, Integer quantity, String image, String description, String odometer, String gearshift, String type, float discount, ProductBrand productBrand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.odometer = odometer;
        this.gearshift = gearshift;
        this.type = type;
        this.discount = discount;
        this.productBrand = productBrand;
    }

    public String getProductBrand() {
        return productBrand!=null ?productBrand.getName():"";
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", odometer='" + odometer + '\'' +
                ", gearshift='" + gearshift + '\'' +
                ", type='" + type + '\'' +
                ", discount=" + discount +
                ", productBrand=" + productBrand +
                '}';
    }
}
