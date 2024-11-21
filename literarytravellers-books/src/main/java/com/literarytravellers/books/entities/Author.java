package com.literarytravellers.books.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Classe que representa um autor
 */
@Entity
@Table(name = "tb_author")
@Data // simplifica getters, setters, toString, etc.
public class Author {

    /**
     * Identificador único do autor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do autor
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Livros escritos pelo autor
     * Muitos autores podem ter vários livros e vice-versa
     */
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}
