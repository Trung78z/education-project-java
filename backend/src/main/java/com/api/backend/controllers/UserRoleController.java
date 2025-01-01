package com.api.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.api.backend.models.user.UserRole;
import com.api.backend.services.UserRoleService;
import com.api.backend.utils.ResponseWrapper;

@Controller
@RequestMapping("/api/v1/role-user")
public class UserRoleController {
    final private UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<UserRole>>> getRole() {
        List<UserRole> listRoles = userRoleService.getRoleUsers();
        return ResponseEntity.ok(new ResponseWrapper<>(true, 200, listRoles));
    }

    /**
     * @param role
     * @return
     */
    @PostMapping
    public ResponseEntity<ResponseWrapper<UserRole>> addRole(@RequestBody UserRole role) {
        try {
            UserRole res = userRoleService.save(role);
            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, res));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserRole>> updateRole(@PathVariable Integer id,
            @RequestBody UserRole entity) {
        // TODO: process PUT request
        try {
            UserRole res = userRoleService.updateRole(id, entity);
            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, res));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));

        }
    }

}
