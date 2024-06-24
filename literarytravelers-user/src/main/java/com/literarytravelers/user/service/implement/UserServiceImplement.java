package com.literarytravelers.user.service.implement;

import com.literarytravelers.user.entities.User;
import com.literarytravelers.user.repository.UserRepository;
import com.literarytravelers.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
