package com.literarytravelers.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.literarytravelers.user.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
}

