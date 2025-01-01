package com.api.backend.models.user;

import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user_roles")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;
    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Users> users;

    public UserRole() {
    }

    public UserRole(Integer id, String roleName, List<Users> users) {
        this.id = id;
        this.roleName = roleName;
        this.users = users;
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

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

}
