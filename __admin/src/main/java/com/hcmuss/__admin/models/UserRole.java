package com.hcmuss.__admin.models;

import java.util.List;

public class UserRole {


    private Integer id;


    private String roleName;


    private List<User> users;

    public UserRole() {
    }

    public UserRole(Integer id, String roleName, List<User> users) {
        this.id = id;
        this.roleName = roleName;
        this.users = users;
    }
    public UserRole(String roleName) {
        this.roleName = roleName;
    }
    public UserRole(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    // Getter và Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
