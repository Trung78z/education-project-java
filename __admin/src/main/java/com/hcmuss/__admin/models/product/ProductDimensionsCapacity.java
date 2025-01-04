package com.hcmuss.__admin.models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDimensionsCapacity {

    private String length;
    private String height;
    private String wheelbase;
    private String heightWithRoofRails;
    private int luggageCapacitySeatsUp;
    private int luggageCapacitySeatsDown;
    private String width;
    private String widthWithMirrors;
    private int grossVehicleWeight;
    private int maxLoadingWeight;
    private int maxRoofLoad;
    private int numberOfSeats;

    public ProductDimensionsCapacity() {
    }

    public ProductDimensionsCapacity(String length, String height, String wheelbase, String heightWithRoofRails, int luggageCapacitySeatsUp, int luggageCapacitySeatsDown, String width, String widthWithMirrors, int grossVehicleWeight, int maxLoadingWeight, int maxRoofLoad, int numberOfSeats) {

        this.length = length;
        this.height = height;
        this.wheelbase = wheelbase;
        this.heightWithRoofRails = heightWithRoofRails;
        this.luggageCapacitySeatsUp = luggageCapacitySeatsUp;
        this.luggageCapacitySeatsDown = luggageCapacitySeatsDown;
        this.width = width;
        this.widthWithMirrors = widthWithMirrors;
        this.grossVehicleWeight = grossVehicleWeight;
        this.maxLoadingWeight = maxLoadingWeight;
        this.maxRoofLoad = maxRoofLoad;
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "ProductDimensionsCapacity{" + "length='" + length + '\'' + ", height='" + height + '\'' + ", wheelbase='" + wheelbase + '\'' + ", heightWithRoofRails='" + heightWithRoofRails + '\'' + ", luggageCapacitySeatsUp=" + luggageCapacitySeatsUp + ", luggageCapacitySeatsDown=" + luggageCapacitySeatsDown + ", width='" + width + '\'' + ", widthWithMirrors='" + widthWithMirrors + '\'' + ", grossVehicleWeight=" + grossVehicleWeight + ", maxLoadingWeight=" + maxLoadingWeight + ", maxRoofLoad=" + maxRoofLoad + ", numberOfSeats=" + numberOfSeats + '}';
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWheelbase() {
        return wheelbase;
    }

    public void setWheelbase(String wheelbase) {
        this.wheelbase = wheelbase;
    }

    public String getHeightWithRoofRails() {
        return heightWithRoofRails;
    }

    public void setHeightWithRoofRails(String heightWithRoofRails) {
        this.heightWithRoofRails = heightWithRoofRails;
    }

    public int getLuggageCapacitySeatsUp() {
        return luggageCapacitySeatsUp;
    }

    public void setLuggageCapacitySeatsUp(int luggageCapacitySeatsUp) {
        this.luggageCapacitySeatsUp = luggageCapacitySeatsUp;
    }

    public int getLuggageCapacitySeatsDown() {
        return luggageCapacitySeatsDown;
    }

    public void setLuggageCapacitySeatsDown(int luggageCapacitySeatsDown) {
        this.luggageCapacitySeatsDown = luggageCapacitySeatsDown;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getWidthWithMirrors() {
        return widthWithMirrors;
    }

    public void setWidthWithMirrors(String widthWithMirrors) {
        this.widthWithMirrors = widthWithMirrors;
    }

    public int getGrossVehicleWeight() {
        return grossVehicleWeight;
    }

    public void setGrossVehicleWeight(int grossVehicleWeight) {
        this.grossVehicleWeight = grossVehicleWeight;
    }

    public int getMaxLoadingWeight() {
        return maxLoadingWeight;
    }

    public void setMaxLoadingWeight(int maxLoadingWeight) {
        this.maxLoadingWeight = maxLoadingWeight;
    }

    public int getMaxRoofLoad() {
        return maxRoofLoad;
    }

    public void setMaxRoofLoad(int maxRoofLoad) {
        this.maxRoofLoad = maxRoofLoad;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
