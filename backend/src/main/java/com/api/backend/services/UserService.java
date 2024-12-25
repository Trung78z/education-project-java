package com.api.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.backend.models.user.Users;
import com.api.backend.models.user.UserRole;
import com.api.backend.repositories.UserRepository;
import com.api.backend.repositories.UserRoleRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public String verify(Users user) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
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
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(user);

        } else {
            throw new RuntimeException("Role not found");
        }

    }

    public Users getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Users not found"));
    }

}
