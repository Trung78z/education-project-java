package com.api.backend.models.product;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "productInterior")
@Component
public class ProductInterior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductInterior() {
    }

    public ProductInterior(String name, Product product) {
        this.name = name;
        this.product = product;
    }

    public ProductInterior(Integer id, String name, Product product) {
        this.id = id;
        this.name = name;
        this.product = product;
    }

    public ProductInterior(String name) {
        this.name = name;
    }

    public ProductInterior(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductInterior [id=" + id + ", name=" + name + ", product=" + product + "]";
    }

}
