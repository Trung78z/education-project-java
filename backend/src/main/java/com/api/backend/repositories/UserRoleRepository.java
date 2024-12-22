package com.api.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.backend.models.user.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @SuppressWarnings("null")
    Optional<UserRole> findById(Integer id);

    UserRole findByRoleName(String roleName);

}
