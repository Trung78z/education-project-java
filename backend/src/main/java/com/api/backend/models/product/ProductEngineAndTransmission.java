package com.api.backend.models.product;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "productEngineAndTransmission")
@Component
public class ProductEngineAndTransmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer fuelTankCapacity;
    private Integer maxTowingWeightBraked;
    private Integer maxTowingWeightUnbraked;
    private Integer minimumKerbweight;
    private Integer turningCircleKerbToKerb;

    @JsonBackReference
    @OneToOne()
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductEngineAndTransmission() {
    }

    public ProductEngineAndTransmission(Integer fuelTankCapacity, Integer maxTowingWeightBraked,
            Integer maxTowingWeightUnbraked, Integer minimumKerbweight, Integer turningCircleKerbToKerb,
            Product product) {
        this.fuelTankCapacity = fuelTankCapacity;
        this.maxTowingWeightBraked = maxTowingWeightBraked;
        this.maxTowingWeightUnbraked = maxTowingWeightUnbraked;
        this.minimumKerbweight = minimumKerbweight;
        this.turningCircleKerbToKerb = turningCircleKerbToKerb;
        this.product = product;
    }

    public Integer getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(Integer fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public Integer getMaxTowingWeightBraked() {
        return maxTowingWeightBraked;
    }

    public void setMaxTowingWeightBraked(Integer maxTowingWeightBraked) {
        this.maxTowingWeightBraked = maxTowingWeightBraked;
    }

    public Integer getMaxTowingWeightUnbraked() {
        return maxTowingWeightUnbraked;
    }

    public void setMaxTowingWeightUnbraked(Integer maxTowingWeightUnbraked) {
        this.maxTowingWeightUnbraked = maxTowingWeightUnbraked;
    }

    public Integer getMinimumKerbweight() {
        return minimumKerbweight;
    }

    public void setMinimumKerbweight(Integer minimumKerbweight) {
        this.minimumKerbweight = minimumKerbweight;
    }

    public Integer getTurningCircleKerbToKerb() {
        return turningCircleKerbToKerb;
    }

    public void setTurningCircleKerbToKerb(Integer turningCircleKerbToKerb) {
        this.turningCircleKerbToKerb = turningCircleKerbToKerb;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductEngineAndTransmission [fuelTankCapacity=" + fuelTankCapacity + ", maxTowingWeightBraked="
                + maxTowingWeightBraked + ", maxTowingWeightUnbraked=" + maxTowingWeightUnbraked
                + ", minimumKerbweight=" + minimumKerbweight + ", turningCircleKerbToKerb=" + turningCircleKerbToKerb
                + ", product=" + product + "]";
    }

}
