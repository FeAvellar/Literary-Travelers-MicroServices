package com.literarytravelers.user.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.literarytravelers.user.entities.Role;
import com.literarytravelers.user.repository.RoleRepository;
import com.literarytravelers.user.service.RoleService;

@Service
public class RoleServiceImplement implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }
}