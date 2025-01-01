package com.api.backend.dto;

public class UserToken {
    private String token;

    public UserToken() {
    }

    public UserToken(String token) {
        this.token = token;

    }

    public UserToken(String token, String username) {
        this.token = token;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
