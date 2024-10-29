package com.app.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.api.exceptions.ResourceNotFoundException;
import com.app.api.model.User;
import com.app.api.repositories.UserRepository;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    @Transactional
    public User updateUser(Integer id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (updatedUser.getEmail() != null)
                existingUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getPassword() != null)
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            return userRepository.save(existingUser);
        } else {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
    }
}