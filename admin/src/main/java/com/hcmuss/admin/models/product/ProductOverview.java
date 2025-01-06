package com.hcmuss.admin.models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOverview {
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

    @Override
    public String toString() {
        return "ProductOverview{" +
                "body='" + body + '\'' +
                ", productCondition='" + productCondition + '\'' +
                ", mileage=" + mileage +
                ", engineSize=" + engineSize +
                ", fuelType='" + fuelType + '\'' +
                ", doors=" + doors +
                ", year=" + year +
                ", cylinders=" + cylinders +
                ", transmission='" + transmission + '\'' +
                ", color='" + color + '\'' +
                ", driveType='" + driveType + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
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

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCylinders() {
        return cylinders;
    }

    public void setCylinders(Integer cylinders) {
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
}
