package com.api.backend.dto;

import com.api.backend.models.user.Role;

public class UserRoleDTO {

    private Integer id;
    private String roleName;

    public UserRoleDTO() {
    }

    public UserRoleDTO(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public UserRoleDTO(Role role) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
    }

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

    @Override
    public String toString() {
        return "UserRoleDTO [id=" + id + ", roleName=" + roleName + "]";
    }

}
