package com.literarytravelers.book.repository;

import com.literarytravelers.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Buscar livros por título
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Buscar livros por descrição (com uma palavra-chave)
    List<Book> findByDescriptionContainingIgnoreCase(String keyword);

    // Buscar livros por autor
    List<Book> findByAuthorsNameContainingIgnoreCase(String authorName);

    // Buscar livros por categoria
    List<Book> findByCategoriesName(String categoryName);
}
