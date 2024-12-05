package com.app.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.server.model.User;

import java.util.Optional;

public interface AuthorCationRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

}
