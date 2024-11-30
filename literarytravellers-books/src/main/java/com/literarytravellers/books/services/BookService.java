package com.literarytravellers.books.services;

import java.util.List;

import com.literarytravellers.books.entities.Book;

public interface BookService {

    Book createBook(Book book);

    Book updateBook(Book book);

    Book getBookById(Long id);

    void deleteBookById(Long id);

    public List<Book> getAllBooks();

    public List<Book> getBooksByAuthor(String authorName);

    public List<Book> getBooksByPublisher(String publisher);

    public List<Book> getBooksByTitle(String title);

    public List<Book> getBooksByCategory(String category);

    public List<Book> getBooksByEdition(String edition);

}
