package com.api.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.api.backend.models.user.Role;
import com.api.backend.services.UserRoleService;
import com.api.backend.utils.ResponseWrapper;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/api/v1/role-user")
public class UserRoleController {
    final private UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<Role>>> getRole() {
        List<Role> listRoles = userRoleService.getRoleUsers();
        return ResponseEntity.ok(new ResponseWrapper<>(true, 200, listRoles));
    }

    /**
     * @param role
     * @return
     */
    @PostMapping
    public ResponseEntity<ResponseWrapper<Role>> addRole(@RequestBody Role role) {
        try {
            Role res = userRoleService.save(role);
            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, res));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(false, 400, e.getMessage()));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(false, 500, "Internal Server Error"));

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Role>> updateRole(@PathVariable Integer id, @RequestBody Role entity) {
        // TODO: process PUT request
        try {
            Role res = userRoleService.updateRole(id, entity);
            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, res));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(false, 400, e.getMessage()));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(false, 500, "Internal Server Error"));

        }
    }

}
