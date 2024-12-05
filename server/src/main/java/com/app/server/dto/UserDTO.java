package com.app.server.dto;

import com.app.server.model.Profile;

import java.time.LocalDate;
import java.time.Period;

public class UserDTO {

    private String id;
    private String username;
    private String email;
    private String phone;
    private LocalDate birthDay;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Integer age;
    private Profile profile;

    public UserDTO() {
    }

    public UserDTO(String username, String email,  String phone, LocalDate birthDay, LocalDate createdAt,
                LocalDate updatedAt, Integer age) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.birthDay = birthDay;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.age = age;
    }

    public UserDTO(String id, String username, String email, String phone, LocalDate birthDay,
                LocalDate createdAt, LocalDate updatedAt, Integer age) {
        this.id = id;
        this.username = username;
        this.email = email;
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
