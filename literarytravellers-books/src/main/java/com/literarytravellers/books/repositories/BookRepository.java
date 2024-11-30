package com.literarytravellers.books.repositories;

import com.literarytravellers.books.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // busca livros cujo título contém a string fornecida (ignorando maiúsculas e
    // minúsculas).
    List<Book> findByTitleContainingIgnoreCase(String title);

    // busca livros cuja descrição contém a string fornecida (ignorando maiúsculas e
    // minúsculas) - (com uma palavra-chave)
    List<Book> findByDescriptionContainingIgnoreCase(String keyword);

    // busca livros cujo autor contém a string fornecida (ignorando maiúsculas e
    // minúsculas).
    List<Book> findByAuthorsNameContainingIgnoreCase(String authorName);

    // busca livros cuja categoria contém a string fornecida.
    List<Book> findByCategoriesNameContainingIgnoreCase(String categoryName);

    // busca livros cuja edição contém a string fornecida.
    List<Book> findByEditionContainingIgnoreCase(String edition);

    // busca livros cuja editora contém a string fornecida.
    List<Book> findByPublisherContainingIgnoreCase(String publisher);
}
