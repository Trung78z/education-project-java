package com.api.backend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.api.backend.models.user.User;
import com.api.backend.services.UserService;
import com.api.backend.utils.ResponseWrapper;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<User>>> getUser() {
        List<User> listUser = userService.getUsers();
        return ResponseEntity.ok(new ResponseWrapper<>(true, 200, listUser));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
