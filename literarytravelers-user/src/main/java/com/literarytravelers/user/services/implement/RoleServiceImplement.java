package com.literarytravelers.user.services.implement;


import com.literarytravelers.user.entities.Role;
import com.literarytravelers.user.repositories.RoleRepository;
import com.literarytravelers.user.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplement implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Long id, Role roleDetails) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.setRoleName(roleDetails.getRoleName());
            role.setDescription(roleDetails.getDescription());
            return roleRepository.save(role);
        } else {
            throw new RuntimeException("Role not found");
        }
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
