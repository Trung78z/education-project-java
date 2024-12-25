package com.api.backend.models.product;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "productDimensionsCapacity")
@Component
public class ProductDimensionsCapacity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductDimensionsCapacity() {
    }

    public ProductDimensionsCapacity(Long id, String length, String height, String wheelbase,
            String heightWithRoofRails, int luggageCapacitySeatsUp, int luggageCapacitySeatsDown, String width,
            String widthWithMirrors, int grossVehicleWeight, int maxLoadingWeight, int maxRoofLoad, int numberOfSeats,
            Product product) {
        this.id = id;
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
        this.product = product;
    }

    public ProductDimensionsCapacity(String length, String height, String wheelbase, String heightWithRoofRails,
            int luggageCapacitySeatsUp, int luggageCapacitySeatsDown, String width, String widthWithMirrors,
            int grossVehicleWeight, int maxLoadingWeight, int maxRoofLoad, int numberOfSeats) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductDimensionsCapacity [id=" + id + ", length=" + length + ", height=" + height + ", wheelbase="
                + wheelbase + ", heightWithRoofRails=" + heightWithRoofRails + ", luggageCapacitySeatsUp="
                + luggageCapacitySeatsUp + ", luggageCapacitySeatsDown=" + luggageCapacitySeatsDown + ", width=" + width
                + ", widthWithMirrors=" + widthWithMirrors + ", grossVehicleWeight=" + grossVehicleWeight
                + ", maxLoadingWeight=" + maxLoadingWeight + ", maxRoofLoad=" + maxRoofLoad + ", numberOfSeats="
                + numberOfSeats + ", product=" + product + "]";
    }

}
