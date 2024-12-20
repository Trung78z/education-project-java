package com.api.backend.models.product;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Component

@Entity
@Table(name = "productComfortConvenience")

public class ProductComfortConvenience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductComfortConvenience() {
    }

    public ProductComfortConvenience(String name) {
        this.name = name;
    }

    public ProductComfortConvenience(Integer id, String name) {
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
        return "ProductComfortConvenience [id=" + id + ", name=" + name + ", product=" + product + "]";
    }

}
