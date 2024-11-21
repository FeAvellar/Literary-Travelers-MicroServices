package com.literarytravelers.book.repository;

import com.literarytravelers.book.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Buscar categorias por nome
    List<Category> findByNameContainingIgnoreCase(String name);

    // Buscar categorias associadas a um livro
    List<Category> findByBooksTitle(String bookTitle);
}
