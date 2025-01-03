package com.api.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.backend.models.user.Users;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    Users findByUsername(String username);

    Users findByEmail(String email);

    Users findByPhone(String phone);

    Users findByUsernameOrEmailOrPhone(String username, String email, String phone);

    @SuppressWarnings("null")
    Optional<Users> findById(UUID id);

    @SuppressWarnings("null")
    @Modifying
    @Transactional
    @Query("DELETE FROM Users u WHERE u.id = ?1")
    void deleteById(UUID id);

}