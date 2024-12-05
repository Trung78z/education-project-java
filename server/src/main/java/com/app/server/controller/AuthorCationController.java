package com.app.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.server.service.AuthorCationService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/auth")

public class AuthorCationController {

    private final AuthorCationService authorCationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final jwtHelper jwtHelper;

    @Autowired
    public AuthorCationController(AuthorCationService authorCationService,
            JwtHelper jwtHelper) {
        this.authorCationService = authorCationService;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Optional<User> userOptional = authorCationService.getUserByEmail(user.getEmail());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("success", false, "message", "User not found!"));
        }

        if (!bCryptPasswordEncoder.matches(user.getPassword(), userOptional.get().getPassword())) {
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "Password is incorrect!"));
        }

        String token = jwtHelper.generateToken(user.getEmail());

        return ResponseEntity.status(200).body(Map.of("success", true, "user", userOptional.get(), "token", token));
    }

}
