package com.literarytravellers.books.repositories;

import com.literarytravellers.books.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório de dados para a entidade Author.
 * Fornece métodos para realizar operações de consulta na base de dados sobre autores.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Encontra uma lista de autores cujo nome contém a string fornecida, ignorando maiúsculas e minúsculas.
     * @param name parte do nome a ser pesquisado.
     * @return lista de autores correspondentes.
     */
    List<Author> findByNameContainingIgnoreCase(String name);

    /**
     * Encontra uma lista de autores que escreveram um livro com o título especificado.
     * @param bookTitle título do livro.
     * @return lista de autores correspondentes.
     */
    List<Author> findByBooksTitle(String bookTitle);

    /**
     * Encontra uma lista de autores associados a uma editora cujo nome contém a string fornecida, ignorando maiúsculas e minúsculas.
     * @param publisher parte do nome da editora a ser pesquisado.
     * @return lista de autores correspondentes.
     */
     @Query("SELECT DISTINCT a FROM Author a JOIN a.editions e WHERE LOWER(e.publisher) LIKE LOWER(CONCAT('%', :publisher, '%'))")
    List<Author> findAuthorsByPublisher(@Param("publisher") String publisher);

    @Query("SELECT DISTINCT a FROM Author a JOIN a.categories c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :categoryName, '%'))")
    List<Author> findByCategoryNameContainingIgnoreCase(@Param("categoryName") String categoryName);
}

