package com.hcmuss.__admin.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPayload {
    @JsonProperty
    private String username;

    @JsonProperty
    private String phone;
    @JsonProperty
    private String password;
    @JsonProperty
    private String email;

    @JsonProperty
    private String fullName;

    @JsonProperty
    private Role userRole;

    @JsonProperty
    private String address;

    public UserPayload() {
    }

    public UserPayload(String username, String phone, String password, String email, String fullName, Role userRole, String address) {
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.userRole = userRole;
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserPayload{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
