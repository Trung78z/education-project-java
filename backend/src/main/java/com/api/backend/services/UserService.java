package com.api.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.backend.models.user.User;
import com.api.backend.models.user.UserRole;
import com.api.backend.repositories.UserRepository;
import com.api.backend.repositories.UserRoleRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        Optional<UserRole> exists = userRoleRepository.findById(user.getUserRole().getId());
        User existsUserEmail = userRepository.findByEmail(user.getEmail());
        if (existsUserEmail != null) {
            throw new RuntimeException("User email already exists");
        }
        User existsUserName = userRepository.findByUsername(user.getUsername());
        if (existsUserName != null) {
            throw new RuntimeException("User name already exists");
        }
        if (exists.isPresent()) {
            user.setUserRole(exists.get());
            return userRepository.save(user);

        } else {
            throw new RuntimeException("Role not found");
        }

    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
