package com.literarytravellers.books.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Essa classe representa a entidade Livro (Book) no banco de dados.
 */
@Entity
@Table(name = "tb_book")
@Data // simplifica getters, setters, toString, etc.
public class Book {

    /**
     * Identificador único do livro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título do livro.
     */
    @Column(nullable = false, length = 100)
    @NotBlank(message = "{NotBlank.title}")
    @Size(max = 255, message = "Title must be less than 255 characters")
    private String title;

    /**
     * Descrição do livro.
     */
    @Column(nullable = false, length = 100)
    @NotBlank(message = "{NotBlank.description}")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    /**
     * Autores do livro.
     */
    @ManyToMany
    @JoinTable( //As tabelas serão geradas automaticamente pelo JPA
        name = "book_author",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    /**
     * Categorias do livro.
     */
    @ManyToMany
    @JoinTable( //As tabelas serão geradas automaticamente pelo JPA
        name = "book_category",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    /**
     * Edições do livro.
     */
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true) //O uso de CascadeType.ALL está correto se você quiser que as edições sejam criadas, atualizadas ou removidas automaticamente junto com o livro.
    @JsonManagedReference
    private List<Edition> edition;
    
}

