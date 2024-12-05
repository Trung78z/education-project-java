package com.app.server.controller;

import com.app.server.model.User;
import com.app.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUser() {
        List<User> users = userService.getAllUsers();
        List<Map<String, Object>> userProfile = users.stream().map(item -> {
            Map<String, Object> profile = new HashMap<>();
            profile.put("id", item.getId());
            profile.put("username", item.getUsername());
            profile.put("email", item.getEmail());
            profile.put("phone", item.getPhone());
            profile.put("birthDay", item.getBirthDay());
            profile.put("age", item.getAge());

            if (item.getProfile() != null) {
                profile.put("profile", Map.of(
                        "firstName", item.getProfile().getFirstName(),
                        "lastName", item.getProfile().getLastName(),
                        "bankAccountNumber", item.getProfile().getBankAccountNumber(),
                        "total", item.getProfile().getTotal(),
                        "bankAccountType", item.getProfile().getBankAccountType(),
                        "createdAt", item.getProfile().getCreatedAt(),
                        "updatedAt", item.getProfile().getUpdatedAt()));
            } else {
                profile.put("profile", null);
            }

            return profile;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", userProfile);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody User user) {
        Optional<User> emailOptional = userService.getUserByEmail(user.getEmail());
        Optional<User> userNameOptional = userService.getUserByUsername(user.getUsername());
        Optional<User> phoneOptional = userService.getUserByPhone(user.getPhone());

        if (emailOptional.isPresent()) {
            return ResponseEntity.status(409).body(Map.of("success", false, "message", "User email already exists"));
        }

        if (userNameOptional.isPresent()) {
            return ResponseEntity.status(409).body(Map.of("success", false, "message", "User name already exists"));
        }
        if (phoneOptional.isPresent()) {
            return ResponseEntity.status(409).body(Map.of("success", false, "message", "Phone already exists"));
        }
        User savedUser = userService.SaveUser(user);
        return ResponseEntity.status(201).body(Map.of("success", true, "message", savedUser));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("userId") String userId,
            @RequestBody User user) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("success", false, "message", "User not found"));
        }
        userService.editUser(userOptional.get(), user);
        return ResponseEntity.status(200)
                .body(Map.of("success", true, "message", "Edit user with ID: " + userId + " successfully!"));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String userId) {
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("success", false, "message", "User not found"));
        }
        userService.deleteUser(userId);
        return ResponseEntity.status(200)
                .body(Map.of("success", true, "message", "User deleted with id " + userId + " successfully"));
    }
}
