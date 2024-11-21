package com.literarytravelers.book.repository;

import com.literarytravelers.book.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Buscar autor por nome
    List<Author> findByNameContainingIgnoreCase(String name);

    // Buscar todos os autores que escreveram um livro específico
    List<Author> findByBooksTitle(String bookTitle);
}
