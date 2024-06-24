package com.literarytravelers.user.service;

import com.literarytravelers.user.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getAllUsers();

    public Optional<User> getUserById(Long id);

    public User saveUser(User user);

    public void deleteUser(Long id);
}
