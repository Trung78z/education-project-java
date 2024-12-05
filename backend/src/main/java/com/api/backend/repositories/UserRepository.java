package com.api.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.backend.models.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}