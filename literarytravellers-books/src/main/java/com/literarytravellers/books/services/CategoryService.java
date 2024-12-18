package com.literarytravellers.books.services;

import java.util.List;

import com.literarytravellers.books.entities.Author;
import com.literarytravellers.books.entities.Book;
import com.literarytravellers.books.entities.Category;

public interface CategoryService {

    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    void deleteCategory(Long id);

    // Operações adicionais
    List<Book> getBooksByCategory(String categoryName);
    public List<Category> getCategoriesByAuthor(String authorName);
    

    

}
