package com.api.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.backend.models.user.Role;
import com.api.backend.repositories.UserRoleRepository;

@Service
public class UserRoleService {
    final private UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<Role> getRoleUsers() {
        return userRoleRepository.findAll();
    }

    public Role save(Role role) {
        Role exists = userRoleRepository.findByRoleName(role.getRoleName());
        System.out.println(exists);
        if (exists != null) {
            throw new RuntimeException("Role is existed!");
        }
        return userRoleRepository.save(role);
    }

    public Role updateRole(Integer id, Role role) {
        Optional<Role> exists = userRoleRepository.findById(id);
        if (exists.isPresent()) {
            Role existingRole = exists.get();
            if (role.getRoleName() != null) {
                existingRole.setRoleName(role.getRoleName());
            }
            return userRoleRepository.save(existingRole);
        } else {
            throw new RuntimeException("Role is not existed!");
        }
    }

    public void delete(Integer id) {
        Optional<Role> exists = userRoleRepository.findById(id);
        if (exists.isPresent()) {

            userRoleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Role is not existed!");
        }
    }

}
