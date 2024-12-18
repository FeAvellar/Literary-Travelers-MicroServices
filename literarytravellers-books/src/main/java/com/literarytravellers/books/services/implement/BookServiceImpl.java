package com.literarytravellers.books.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.literarytravellers.books.entities.Book;
import com.literarytravellers.books.exceptions.ApplicationException;
import com.literarytravellers.books.repositories.BookRepository;
import com.literarytravellers.books.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        if (!bookRepository.existsById(id)) {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "Livro não encontrado com o ID: " + id);
        }
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND,"Livro não encontrado"));
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "Nenhum livro encontrado.");
        }
        return books;
    }

    @Override
    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.findByAuthorsNameContainingIgnoreCase(authorName);
    }

    @Override
    public List<Book> getBooksByEdition(String edition) {

        return bookRepository.findByEditionContainingIgnoreCase(edition);
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);

    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findByCategoriesNameContainingIgnoreCase(category);
    }

    @Override
    public List<Book> getBooksByPublisher(String publisher) {
        return bookRepository.findByPublisherContainingIgnoreCase(publisher);
    }

    @Override
    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "Livro não encontrado");
        }
        bookRepository.deleteById(id);
    }

}

