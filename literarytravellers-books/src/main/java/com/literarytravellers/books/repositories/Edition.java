package com.literarytravelers.book.repository;

import com.literarytravelers.book.entities.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long> {

    // Buscar edições por ISBN-10
    Optional<Edition> findByIsbn10(String isbn10);

    // Buscar edições por ISBN-13
    Optional<Edition> findByIsbn13(String isbn13);

    // Buscar edições por livro
    List<Edition> findByBookId(Long bookId);

    // Buscar edições por editor
    List<Edition> findByPublisherContainingIgnoreCase(String publisher);
}
