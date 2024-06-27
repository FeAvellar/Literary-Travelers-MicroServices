package com.literarytravelers.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.literarytravelers.user.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
}

