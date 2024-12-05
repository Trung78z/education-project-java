package com.api.backend.models.product;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // @JsonBackReference
    @JsonManagedReference

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_brand_id", nullable = false)
    private ProductBrand productBrand;

    @JsonManagedReference
    @JsonProperty("interior")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductInterior> interior;

    @JsonManagedReference
    @JsonProperty("exterior")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductExterior> exterior;

    @JsonManagedReference
    @JsonProperty("safety")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductSafety> safety;

    @JsonManagedReference
    @JsonProperty("comfortConvenience")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductComfortConvenience> comfortConvenience;

    @JsonManagedReference
    @JsonProperty("overview")
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private ProductOverview overview;

    @JsonManagedReference
    @JsonProperty("dimensionsCapacity")
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private ProductDimensionsCapacity dimensionsCapacity;

    @JsonManagedReference
    @JsonProperty("engineAndTransmission")
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private ProductEngineAndTransmission engineAndTransmission;

    public Product() {
    }

    public Product(Integer id, String name, double price, Integer quantity, String image, String description,
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

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", image="
                + image + ", description=" + description + ", model=" + model + ", size=" + size + ", type=" + type
                + ", discount=" + discount + ", productBrand=" + productBrand + ", interior=" + interior + ", exterior="
                + exterior + ", safety=" + safety + ", comfortConvenience=" + comfortConvenience + ", overview="
                + overview + ", dimensionsCapacity=" + dimensionsCapacity + ", engineAndTransmission="
                + engineAndTransmission + "]";
    }

}