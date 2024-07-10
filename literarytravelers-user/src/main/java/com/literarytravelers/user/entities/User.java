package com.literarytravelers.user.entities;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "tb_user")
@Data
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank (message = "{NotBlank.full.name}")
    @Size(max = 255, message = "Name must be less than 255 characters")
    @Pattern(regexp = "^[A-Z]+(.)*", message = "Name must contain only letters and spaces")
    private String fullname; // Nome completo

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Login name is mandatory")
    @Size(min = 5, max = 15, message = "{Size.login.name}")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "{Pattern.user.username}")
    private String loginName;

    public void setloginName(String loginName) {
        this.loginName = loginName != null ? loginName.toUpperCase() : null;  //Serve para deixar todas as letras do login name maiusculas
    }

    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "{Size.user.password}")
    private String password;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "{Email.user.email}")
    @Size(max = 255, message = "{Size.user.email}")
    private String email;

    @Column(nullable = false, unique = true)
    @CPF(message = "{Cpf.user.cpf}")
    @Size(max = 11, message = "{Size.user.cpf}")
    private String cpf;

    @Column(nullable = false, unique = false)
    @NotNull(message = "Birth date is mandatory")
    @Past(message = "Birth date must be in the past")
    private Date birthDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Valid
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Valid
    private List<Phone> phones;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Valid
    private List<Address> address;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Valid
    private Photo photo;

    // Métodos UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Considera que a conta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Considera que a conta nunca está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Considera que as credenciais nunca expiram
    }

    @Override
    public boolean isEnabled() {
        return true; // Considera que o usuário está sempre ativo
    }

    @Override
    public String getUsername() {
        return loginName;
    }

}