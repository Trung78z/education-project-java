package com.app.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "users")
@Component
public class User {


    @Id
    // @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    @Column(unique = true, updatable = false)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phone;
    private LocalDate birthDay;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
    @Transient
    private Integer age;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;

    public User() {
    }

    public User(String username, String email, String password, String phone, LocalDate birthDay, LocalDate createdAt,
                LocalDate updatedAt, Integer age) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthDay = birthDay;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.age = age;
    }

    public User(String id, String username, String email, String password, String phone, LocalDate birthDay,
                LocalDate createdAt, LocalDate updatedAt, Integer age) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthDay = birthDay;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.age = age;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getAge() {
        return Period.between(this.birthDay, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", username='" + username + '\'' + ", " + "email='" + email + '\'' + ", phone='" + phone + '\'' + ", birthDay=" + birthDay + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", age=" + age + '}';
    }
}
