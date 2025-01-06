package com.hcmuss.admin.models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEngineAndTransmission {

    private Integer fuelTankCapacity;
    private Integer maxTowingWeightBraked;
    private Integer maxTowingWeightUnbraked;
    private Integer minimumKerbweight;
    private Integer turningCircleKerbToKerb;


    public ProductEngineAndTransmission() {
    }

    public ProductEngineAndTransmission(Integer fuelTankCapacity, Integer maxTowingWeightBraked, Integer maxTowingWeightUnbraked, Integer minimumKerbweight, Integer turningCircleKerbToKerb) {

        this.fuelTankCapacity = fuelTankCapacity;
        this.maxTowingWeightBraked = maxTowingWeightBraked;
        this.maxTowingWeightUnbraked = maxTowingWeightUnbraked;
        this.minimumKerbweight = minimumKerbweight;
        this.turningCircleKerbToKerb = turningCircleKerbToKerb;
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
}
