package com.literarytravelers.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.literarytravelers.user.entities.User;
import com.literarytravelers.user.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService; //Injeta a dependência do serviço UserService, permitindo que o controlador chame métodos do serviço (service) para executar operações nos usuários.

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE) //Define um endpoint HTTP GET para /user, que retorna todos os usuários cadastrados.
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE) //Define um endpoint HTTP POST para /user, usado para salvar um novo usuário.
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
public ResponseEntity<User> updatedUser(@PathVariable Long id, @RequestBody User user) {
    if (!id.equals(user.getId())) {
        return ResponseEntity.badRequest().build(); // Retorna BadRequest se os IDs não coincidirem
    }

    User updatedUser = userService.updateUser(user);
    if (updatedUser != null) {
        return new ResponseEntity<>(updatedUser, HttpStatus.OK); // Retorna o usuário atualizado com status OK (200)
    } else {
        return ResponseEntity.notFound().build(); // Retorna NotFound se o usuário com o ID especificado não foi encontrado
    }
}

    @DeleteMapping("/{id}") //Define um endpoint HTTP DELETE para /user/{id}, usado para excluir um usuário pelo ID.
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
