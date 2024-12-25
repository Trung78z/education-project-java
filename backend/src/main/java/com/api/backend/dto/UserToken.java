package com.api.backend.dto;

import com.api.backend.models.user.Users;

public class UserToken {
    private String token;
    private String username;

    public UserToken() {
    }

    public UserToken(String token, Users user) {
        this.token = token;
        this.username = user.getUsername();
    }

    public UserToken(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
