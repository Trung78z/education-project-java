package com.api.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.backend.models.user.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByPhone(String phone);

    User findByUsernameOrEmailOrPhone(String username, String email, String phone);

    @SuppressWarnings("null")
    Optional<User> findById(UUID id);
}