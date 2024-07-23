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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.literarytravelers.user.entities.Address;
import com.literarytravelers.user.entities.Phone;
import com.literarytravelers.user.entities.User;
import com.literarytravelers.user.exceptions.ApplicationException;
import com.literarytravelers.user.repositories.UserRepository;

@Component
public class UserValidator {

    @Autowired UserRepository userRepository;

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

    public void validateUpdate(User newUser) {
        User existingUser = userRepository.findById(newUser.getId())
            .orElseThrow(() -> new ApplicationException("User not found", HttpStatus.NOT_FOUND));

        if (!newUser.getLoginName().equals(existingUser.getLoginName())) {
            validateLoginName(newUser.getLoginName());
        }

        if (!newUser.getEmail().equals(existingUser.getEmail())) {
            validateEmail(newUser.getEmail());
        }

        if (!newUser.getCpf().equals(existingUser.getCpf())) {
            validateCpf(newUser.getCpf());
        }

        if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
            validatePassword(newUser.getPassword());
        }


        validateFullname(newUser.getFullname());
        validateBirthDate(newUser.getBirthDate());
        validatePhones(newUser.getPhones());
        validateAddress(newUser.getAddress());
    }


    public void validateFullname(String fullname) {
        if (!fullname.matches("[a-zA-Z\\s]+")) {
            throw new ApplicationException("Fullname must contain only letters and spaces", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public void validateLoginName(String loginName) {
        if (loginName.length() < 5 || loginName.length() > 15) {
            throw new ApplicationException("Login name must be between 5 and 15 characters", HttpStatus.NOT_ACCEPTABLE);
        }
        if (userRepository.existsByLoginName(loginName)) {
            throw new ApplicationException("A user with this login name already exists", HttpStatus.CONFLICT);
        }
    }

    public void validatePassword(String password) {
        if (password.length() < 6) {
            throw new ApplicationException("Password must be at least 6 characters long", HttpStatus.NOT_ACCEPTABLE);
        }
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()\\-+_]).+$");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new ApplicationException("Password must contain at least one number, one letter, and one special character", HttpStatus.NOT_ACCEPTABLE);
        }
        if (password.matches(".*(012345|123456|234567|345678|456789|567890).*")) {
            throw new ApplicationException("Password must not be a sequence like 123456", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public void validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ApplicationException("Email is not valid", HttpStatus.NOT_ACCEPTABLE);
        }
        if (userRepository.existsByEmail(email)) {
            throw new ApplicationException("A user with this email already exists" , HttpStatus.CONFLICT);
        }
    }

    public void validateCpf(String cpf) {
        if (!cpf.matches("\\d{11}")) {
            throw new ApplicationException("CPF must contain exactly 11 digits", HttpStatus.NOT_ACCEPTABLE);
        }
        if (userRepository.existsByCpf(cpf)) {
            throw new ApplicationException("A user with this CPF already exists", HttpStatus.CONFLICT);
        }
        if (!isValidCpf(cpf)) {
            throw new ApplicationException("CPF is not valid", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public void validateBirthDate(Date date) {
        // Use Calendar for more efficient date comparison
        Calendar minDate = Calendar.getInstance();
        minDate.set(1900, 0, 1);

        Calendar userDate = Calendar.getInstance();
        userDate.setTime(date);

        if (userDate.before(minDate)) {
            throw new ApplicationException("Birth date must be after 01/01/1900", HttpStatus.NOT_ACCEPTABLE);
        }
    }
 public void validatePhones(List<Phone> phones) {
        Set<String> uniquePhoneNumbers = new HashSet<>();
        for (Phone phone : phones) {
            String phoneNumber = phone.getNumber();
            if (!uniquePhoneNumbers.add(phoneNumber)) {
                throw new ApplicationException("Duplicate phone number: " + phoneNumber, HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }
     public void validateAddress(List<Address> list) {
        for (Address address : list) {
            String street = address.getStreet();
            String city = address.getCity();
            String state = address.getState();
            String zipcode = address.getZipcode();
            String number = address.getNumber();
            
            if (street == null || city == null || state == null || zipcode == null || number == null) {
                throw new ApplicationException("Address must contain street, city, state, ZIP and number", HttpStatus.NOT_ACCEPTABLE);
            }
            
            if (!zipcode.matches("\\d{8}")) {
                throw new ApplicationException("Preencha o CEP apenas com números", HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }


    @CPF
    private boolean isValidCpf(String cpf) {
        // Remove any non-digit characters
        cpf = cpf.replaceAll("\\D", "");
    
        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
    
        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
    
        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int mod = sum % 11;
        int firstVerifier = (mod < 2) ? 0 : 11 - mod;
    
        // Verifica o primeiro dígito verificador
        if (firstVerifier != (cpf.charAt(9) - '0')) {
            return false;
        }
    
        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        mod = sum % 11;
        int secondVerifier = (mod < 2) ? 0 : 11 - mod;
    
        // Verifica o segundo dígito verificador
        return secondVerifier == (cpf.charAt(10) - '0');
    }
}