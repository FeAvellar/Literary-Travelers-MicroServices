package com.literarytravellers.books.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Classe que representa a entidade Category.
 * Essa entidade representa uma categoria de livros.
 */
@Entity
@Table(name = "tb_category")
@Data // simplifica getters, setters, toString, etc.
public class Category {

    /**
     * Identificador único da categoria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da categoria.
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Livros que pertencem a essa categoria.
     */
    @ManyToMany(mappedBy = "categories")
    private List<Book> books;
}
