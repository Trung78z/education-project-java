package com.api.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.backend.models.user.Role;
import com.api.backend.models.user.User;
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
        Optional<Role> exists = userRoleRepository.findById(user.getRole().getId());
        if (exists.isPresent()) {

            return userRepository.save(user);
        } else {
            throw new RuntimeException("Role not found");
        }

    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
