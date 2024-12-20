package com.api.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.backend.models.user.Role;

public interface UserRoleRepository extends JpaRepository<Role, Integer> {
    @SuppressWarnings("null")
    Optional<Role> findById(Integer id);

    Role findByRoleName(String roleName);

}
