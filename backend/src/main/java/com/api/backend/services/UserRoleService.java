package com.api.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.backend.models.user.UserRole;
import com.api.backend.repositories.UserRoleRepository;

@Service
public class UserRoleService {
    final private UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRole> getRoleUsers() {
        return userRoleRepository.findAll();
    }

    public UserRole save(UserRole role) {
        UserRole exists = userRoleRepository.findByRoleName(role.getRoleName());

        if (exists != null) {
            throw new RuntimeException("Role is existed!");
        }
        return userRoleRepository.save(role);
    }

    public UserRole updateRole(Integer id, UserRole role) {
        Optional<UserRole> exists = userRoleRepository.findById(id);
        if (exists.isPresent()) {
            UserRole existingRole = exists.get();
            if (role.getRoleName() != null) {
                existingRole.setRoleName(role.getRoleName());
            }
            return userRoleRepository.save(existingRole);
        } else {
            throw new RuntimeException("Role is not existed!");
        }
    }

    public void delete(Integer id) {
        Optional<UserRole> exists = userRoleRepository.findById(id);
        if (exists.isPresent()) {

            userRoleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Role is not existed!");
        }
    }

}
