package com.api.backend.dto;

import java.util.List;

import com.api.backend.models.product.ProductBrand;
import com.api.backend.models.product.ProductComfortConvenience;
import com.api.backend.models.product.ProductDimensionsCapacity;
import com.api.backend.models.product.ProductEngineAndTransmission;
import com.api.backend.models.product.ProductExterior;
import com.api.backend.models.product.ProductInterior;
import com.api.backend.models.product.ProductOverview;
import com.api.backend.models.product.ProductSafety;

public class ProductDTO {
    private Integer id;
    private String name;
    private double price;
    private Integer quantity;
    private String image;
    private String description;
    private String model;
    private String size;
    private String type;
    private float discount;

    private ProductBrand productBrand;

    private List<ProductInterior> interior;

    private List<ProductExterior> exterior;

    private List<ProductSafety> safety;

    private List<ProductComfortConvenience> comfortConvenience;

    private ProductOverview overview;

    private ProductDimensionsCapacity dimensionsCapacity;

    private ProductEngineAndTransmission engineAndTransmission;

    public ProductDTO(Integer id, String name, double price, Integer quantity, String image, String description,
            String model, String size, String type, float discount, ProductBrand productBrand,
            List<ProductInterior> interior, List<ProductExterior> exterior, List<ProductSafety> safety,
            List<ProductComfortConvenience> comfortConvenience, ProductOverview overview,
            ProductDimensionsCapacity dimensionsCapacity, ProductEngineAndTransmission engineAndTransmission) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.model = model;
        this.size = size;
        this.type = type;
        this.discount = discount;
        this.productBrand = productBrand;
        this.interior = interior;
        this.exterior = exterior;
        this.safety = safety;
        this.comfortConvenience = comfortConvenience;
        this.overview = overview;
        this.dimensionsCapacity = dimensionsCapacity;
        this.engineAndTransmission = engineAndTransmission;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public ProductBrand getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(ProductBrand productBrand) {
        this.productBrand = productBrand;
    }

    public List<ProductInterior> getInterior() {
        return interior;
    }

    public void setInterior(List<ProductInterior> interior) {
        this.interior = interior;
    }

    public List<ProductExterior> getExterior() {
        return exterior;
    }

    public void setExterior(List<ProductExterior> exterior) {
        this.exterior = exterior;
    }

    public List<ProductSafety> getSafety() {
        return safety;
    }

    public void setSafety(List<ProductSafety> safety) {
        this.safety = safety;
    }

    public List<ProductComfortConvenience> getComfortConvenience() {
        return comfortConvenience;
    }

    public void setComfortConvenience(List<ProductComfortConvenience> comfortConvenience) {
        this.comfortConvenience = comfortConvenience;
    }

    public ProductOverview getOverview() {
        return overview;
    }

    public void setOverview(ProductOverview overview) {
        this.overview = overview;
    }

    public ProductDimensionsCapacity getDimensionsCapacity() {
        return dimensionsCapacity;
    }

    public void setDimensionsCapacity(ProductDimensionsCapacity dimensionsCapacity) {
        this.dimensionsCapacity = dimensionsCapacity;
    }

    public ProductEngineAndTransmission getEngineAndTransmission() {
        return engineAndTransmission;
    }

    public void setEngineAndTransmission(ProductEngineAndTransmission engineAndTransmission) {
        this.engineAndTransmission = engineAndTransmission;
    }

}