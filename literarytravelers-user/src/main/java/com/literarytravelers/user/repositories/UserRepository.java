package com.literarytravelers.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.literarytravelers.user.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginName(String loginName);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    Object getReferenceById();

}
