package com.literarytravellers.books.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidade de edição de livro
 */
@Entity
@Table(name = "tb_edition")
@Data // simplifica getters, setters, toString, etc.

public class Edition {
    /**
     * ID da edição
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Edição do livro
     * 
     * @Size(max = 255, message = "Ediition must be less than 255 characters")
     */
    @Column(nullable = false, length = 100)
    private String edition;

    /**
     * Editora do livro
     * 
     * @NotBlank (message = "{NotBlank.publisher.name}")
     * @Size(max = 255, message = "Publisher must be less than 255 characters")
     */
    @Column(nullable = false, length = 100)
    private String publisher;

    /**
     * ISBN 10 do livro
     * 
     * @NotBlank (message = "{NotBlank.isbn}")
     * @Size(max = 255, message = "ISBN 10 must be less than 255 characters")
     */
    @Column(nullable = false, length = 100)
    private String isbn10;

    /**
     * ISBN 13 do livro
     * 
     * @NotBlank (message = "{NotBlank.isbn}")
     * @Size(max = 255, message = "ISBN 13 must be less than 255 characters")
     */
    @Column(nullable = false, length = 100)
    private String isbn13;

    /**
     * Número de páginas do livro
     * 
     * @NotBlank (message = "{NotBlank.pages}")
     */
    @Column(nullable = false, length = 100)
    private int pages;

    /**
     * Data de lançamento do livro
     * 
     * @NotBlank (message = "{NotBlank.releaseDate}")
     */
    @Column(nullable = false, length = 100)
    private LocalDate releaseDate;

    /**
     * URL da capa do livro
     * 
     * @NotBlank (message = "{NotBlank.photo}")
     * @Size(max = 255, message = "Cover photo must be less than 255 characters")
     */
    @Column(nullable = false, length = 100)
    private String coverPhoto;

    /**
     * Livro ao qual essa edição pertence
     */
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonBackReference
    private Book book;
}


