package com.literarytravellers.books.services;

import java.util.List;

import com.literarytravellers.books.entities.Author;

public interface AuthorService {

    Author createAuthor(Author author);

    Author updateAuthor(Long id, Author author);

    Author getAuthorById(Long id);

    void deleteAuthorById(Long id);

    public List<Author> getAllAuthors();

    public List<Author> getAuthorsByName(String name);

    public List<Author> getAuthorsByBookTitle(String bookTitle);

    public List<Author> getAuthorsByPublisher(String publisher);

    public List<Author> getAuthorsByCategory(String category);


    
} 