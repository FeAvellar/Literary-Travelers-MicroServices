package com.literarytravelers.user.entities;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_role")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roleName; // papel dele no sistema ROLE_SECRETARIO, ROLE_ADM
    
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;


    @Override
    public String getAuthority() { // retorna o nome do papel, acesso ou
                                   // autorização exemplo ROLE_GERENTE
        return this.roleName;
    }
}