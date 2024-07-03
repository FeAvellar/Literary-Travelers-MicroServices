package com.literarytravelers.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.literarytravelers.user.entities.Role;
import com.literarytravelers.user.service.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping
@RestController

public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<Void> addRole(@Validated @RequestBody Role role) {
         roleService.addRole(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    
}
