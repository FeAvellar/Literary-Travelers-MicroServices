package com.literarytravellers.books.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Classe que representa um autor
 */
@Entity
@Table(name = "tb_authors")
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
     * Data de nascimento do autor
     */
    @Column(nullable = false)
    private LocalDate birthDate;

    /**
     * Data da morte do autor, caso tenha ocorrido
     */
    @Column(nullable = true)
    private LocalDate deathDate;

    /**
     * Biografia do autor
     */
    @Column(nullable = true, columnDefinition = "text")
    private String biography;

    /**
     * Livros escritos pelo autor
     * Muitos autores podem ter vários livros e vice-versa
     */
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    @OneToMany(mappedBy = "author")
    private List<Edition> editions; // Relacionamento com a tabela Editions

    @ManyToMany
    @JoinTable(
        name = "author_category",
        joinColumns = @JoinColumn(name = "author_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
}


