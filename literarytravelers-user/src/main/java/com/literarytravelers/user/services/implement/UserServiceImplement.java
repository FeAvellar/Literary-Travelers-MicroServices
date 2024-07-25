package com.literarytravelers.user.services.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.literarytravelers.user.entities.User;
import com.literarytravelers.user.exceptions.ApplicationException;
import com.literarytravelers.user.repositories.UserRepository;
import com.literarytravelers.user.services.UserService;
import com.literarytravelers.user.validation.UserValidator;

@Service
public class UserServiceImplement implements UserService {
    

   
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ApplicationException("Não foram encontrados usuários", HttpStatus.NOT_FOUND);
        }
        return users;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        userValidator.validate(user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(long l, User user) {
        if (!userRepository.existsById(l)) {
            throw new ApplicationException("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }

        userValidator.validateUpdate(user);

        user.setId(l); //Garanitr que o ID correto seja atualizado
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
