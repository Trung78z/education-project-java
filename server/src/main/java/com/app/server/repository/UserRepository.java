package com.app.server.repository;

import com.app.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByPhone(String phone);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(String id);
}