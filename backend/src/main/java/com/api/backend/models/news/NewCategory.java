package com.api.backend.models.news;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "newCategory")
@Component
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NewCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "newCategory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<New> news;

    public NewCategory() {
    }

    public NewCategory(String name, List<New> news) {
        this.name = name;
        this.news = news;
    }

    public NewCategory(Integer id, String name, List<New> news) {
        this.id = id;
        this.name = name;
        this.news = news;
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

    @Override
    public String toString() {
        return "NewCategory [id=" + id + ", name=" + name + "]";
    }

    public List<New> getNews() {
        return news;
    }

    public void setNews(List<New> news) {
        this.news = news;
    }

}
