package com.api.backend.models.product;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "productOverview")

@Component
public class ProductOverview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String body;
    private String productCondition;
    private Integer mileage;
    private double engineSize;
    private String fuelType;
    private Integer doors;
    private Integer year;
    private Integer cylinders;
    private String transmission;
    private String color;
    private String driveType;
    private String vin;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductOverview() {
    }

    public ProductOverview(String body, String productCondition, Integer mileage, double engineSize, String fuelType,
            Integer doors, Integer year, Integer cylinders, String transmission, String color, String driveType,
            String vin) {
        this.body = body;
        this.productCondition = productCondition;
        this.mileage = mileage;
        this.engineSize = engineSize;
        this.fuelType = fuelType;
        this.doors = doors;
        this.year = year;
        this.cylinders = cylinders;
        this.transmission = transmission;
        this.color = color;
        this.driveType = driveType;
        this.vin = vin;
    }

    public ProductOverview(int id, String body, String productCondition, Integer mileage, double engineSize,
            String fuelType, Integer doors, Integer year, Integer cylinders, String transmission, String color,
            String driveType,
            String vin) {
        this.id = id;
        this.body = body;
        this.productCondition = productCondition;
        this.mileage = mileage;
        this.engineSize = engineSize;
        this.fuelType = fuelType;
        this.doors = doors;
        this.year = year;
        this.cylinders = cylinders;
        this.transmission = transmission;
        this.color = color;
        this.driveType = driveType;
        this.vin = vin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCondition() {
        return productCondition;
    }

    public void setCondition(String condition) {
        this.productCondition = condition;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getCylinders() {
        return cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductOverview [id=" + id + ", body=" + body + ", productCondition=" + productCondition + ", mileage="
                + mileage + ", engineSize=" + engineSize + ", fuelType=" + fuelType + ", doors=" + doors + ", year="
                + year + ", cylinders=" + cylinders + ", transmission=" + transmission + ", color=" + color
                + ", driveType=" + driveType + ", vin=" + vin + ", product=" + product + "]";
    }

}
