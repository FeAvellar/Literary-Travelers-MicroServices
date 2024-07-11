package com.literarytravelers.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.literarytravelers.user.entities.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long>{

    boolean existsByNumber(String number);
    
}
