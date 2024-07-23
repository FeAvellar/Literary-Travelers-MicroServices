package com.literarytravelers.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.literarytravelers.user.entities.User;

import jakarta.validation.Valid;

@Service
public interface UserService  { //Atenção ----- Será interface!!!

    
    
    public List<User> getAllUsers();

    public Optional<User> getUserById(Long id);

    public User saveUser(@Valid User user);

    public User updateUser(long l, User user);

    public void deleteUser(Long id);
}
