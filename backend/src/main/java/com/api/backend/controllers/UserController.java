package com.api.backend.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.api.backend.dto.UserDTO;
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
    public ResponseEntity<ResponseWrapper<List<UserDTO>>> getUser() {

        List<User> listUser = userService.getUsers();
        List<UserDTO> userDTOs = listUser.stream()
                .map(user -> new UserDTO(user))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseWrapper<>(true, 200, userDTOs));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<User>> saveUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, savedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(false, 400, e.getMessage()));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(false, 500, "Internal Server Error"));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<User>> getUserById(@PathVariable UUID id) {

        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, user));
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
