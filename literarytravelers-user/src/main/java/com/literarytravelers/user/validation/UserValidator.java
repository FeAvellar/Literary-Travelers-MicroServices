package com.literarytravelers.user.validation;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.literarytravelers.user.entities.Address;
import com.literarytravelers.user.entities.Phone;
import com.literarytravelers.user.entities.User;
import com.literarytravelers.user.exceptions.ValidacaoException;
import com.literarytravelers.user.repositories.UserRepository;

@Component
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(User user) {
        validateFullname(user.getFullname());
        validateLoginName(user.getLoginName());
        validatePassword(user.getPassword());
        validateEmail(user.getEmail());
        validateCpf(user.getCpf());
        validateBirthDate(user.getBirthDate());
        validatePhones(user.getPhones());
        validateAddress(user.getAddress());
     
    }

    private void validateFullname(String fullname) {
        if (!fullname.matches("[a-zA-Z\\s]+")) {
            throw new ValidacaoException("Fullname must contain only letters and spaces");
        }
    }

    private void validateLoginName(String loginName) {
        if (loginName.length() < 5 || loginName.length() > 15) {
            throw new ValidacaoException("Login name must be between 5 and 15 characters");
        }
        if (userRepository.existsByLoginName(loginName)) {
            throw new ValidacaoException("A user with this login name already exists");
        }
    }

    private void validatePassword(String password) {
        if (password.length() < 6) {
            throw new ValidacaoException("Password must be at least 6 characters long");
        }
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()\\-+_]).+$");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new ValidacaoException("Password must contain at least one number, one letter, and one special character");
        }
        if (password.matches(".*(012345|123456|234567|345678|456789|567890).*")) {
            throw new ValidacaoException("Password must not be a sequence like 123456");
        }
    }

    private void validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ValidacaoException("Email is not valid");
        }
        if (userRepository.existsByEmail(email)) {
            throw new ValidacaoException("A user with this email already exists");
        }
    }

    private void validateCpf(String cpf) {
        if (!cpf.matches("\\d{11}")) {
            throw new ValidacaoException("CPF must contain exactly 11 digits");
        }
        if (userRepository.existsByCpf(cpf)) {
            throw new ValidacaoException("A user with this CPF already exists");
        }
        if (!isValidCpf(cpf)) {
            throw new ValidacaoException("CPF is not valid");
        }
    }

    private void validateBirthDate(Date date) {
        // Use Calendar for more efficient date comparison
        Calendar minDate = Calendar.getInstance();
        minDate.set(1900, 0, 1);

        Calendar userDate = Calendar.getInstance();
        userDate.setTime(date);

        if (userDate.before(minDate)) {
            throw new ValidacaoException("Birth date must be after 01/01/1900");
        }
    }
 private void validatePhones(List<Phone> phones) {
        Set<String> uniquePhoneNumbers = new HashSet<>();
        for (Phone phone : phones) {
            String phoneNumber = phone.getNumber();
            if (!uniquePhoneNumbers.add(phoneNumber)) {
                throw new ValidacaoException("Duplicate phone number: " + phoneNumber);
            }
        }
    }
     private void validateAddress(List<Address> list) {
        for (Address address : list) {
            String street = address.getStreet();
            String city = address.getCity();
            String state = address.getState();
            String zipcode = address.getZipcode();
            String number = address.getNumber();
            
            if (street == null || city == null || state == null || zipcode == null || number == null) {
                throw new ValidacaoException("Address must contain street, city, state, ZIP and number");
            }
            
            if (!zipcode.matches("\\d{5}-\\d{3}")) {
                throw new ValidacaoException("ZIP code must be in the format 12345-123");
            }
        }
    }




    @CPF
    private boolean isValidCpf(String cpf) {
        // Implement a CPF validation algorithm here
        // This is a placeholder for actual CPF validation logic
        return true; // Replace with actual validation logic
    }
}
