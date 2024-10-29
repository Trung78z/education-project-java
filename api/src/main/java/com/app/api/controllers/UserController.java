package com.app.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.app.api.Response.ApiResponse;
import com.app.api.model.User;
import com.app.api.services.UserService;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<User>> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.status(200).body(new ApiResponse<>(200, currentUser));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> allUsers() {
        List<User> users = userService.allUsers();

        return ResponseEntity.ok(new ApiResponse<>(200, users));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<User>> updateUser(@RequestBody User updatedUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        User user = userService.updateUser(currentUser.getId(), updatedUser);

        return ResponseEntity.ok(new ApiResponse<>(200, user));
    }
}