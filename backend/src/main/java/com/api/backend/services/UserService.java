package com.api.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.backend.models.user.Users;

import com.api.backend.models.user.UserRole;
import com.api.backend.repositories.UserRepository;
import com.api.backend.repositories.UserRoleRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Users currentUser = userRepository.findByUsername(username);

        if (currentUser != null && passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(currentUser);
            return true;
        } else {
            return false;
        }
    }

    public Users register(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public String verify(Users user) {
        Users existsUser = userRepository.findByUsername(user.getUsername());

        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername(), existsUser.getUserRole().getRoleName());
        } else {
            return "fail";
        }
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    public Users saveUser(Users user) {
        Optional<UserRole> exists = userRoleRepository.findById(user.getUserRole().getId());
        Users existsUserEmail = userRepository.findByEmail(user.getEmail());
        if (existsUserEmail != null) {
            throw new RuntimeException("Users email already exists");
        }
        Users existsUserName = userRepository.findByUsername(user.getUsername());
        if (existsUserName != null) {
            throw new RuntimeException("Users name already exists");
        }
        if (exists.isPresent()) {
            user.setUserRole(exists.get());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);

        } else {
            throw new RuntimeException("Role not found");
        }

    }

    public Users getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Users not found"));
    }

    @Transactional
    public void deleteUser(UUID userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.deleteById(userId);
    }
}
