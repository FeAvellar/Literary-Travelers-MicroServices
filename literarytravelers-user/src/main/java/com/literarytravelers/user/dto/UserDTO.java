package com.literarytravelers.user.dto;

import java.util.Date;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    
    private Long id;

    @NotBlank(message = "{NotBlank.full.name}")
    @Size(max = 255, message = "Name must be less than 255 characters")
    @Pattern(regexp = "^[A-Z]+(.)*", message = "Name must contain only letters and spaces")
    private String fullname;

    @NotBlank(message = "Login name is mandatory")
    @Size(min = 5, max = 15, message = "{Size.login.name}")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "{Pattern.user.username}")
    private String loginName;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "{Size.user.password}")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "{Email.user.email}")
    @Size(max = 255, message = "{Size.user.email}")
    private String email;

    @NotBlank(message = "CPF is mandatory")
    @Pattern(regexp = "\\d{11}", message = "CPF must be 11 digits")
    private String cpf;

    @NotNull(message = "Birth date is mandatory")
    @Past(message = "Birth date must be in the past")
    private Date birthDate;

}

