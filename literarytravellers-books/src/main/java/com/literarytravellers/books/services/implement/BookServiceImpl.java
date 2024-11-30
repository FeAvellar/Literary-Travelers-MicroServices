package com.literarytravellers.books.services.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.literarytravellers.books.entities.Book;
import com.literarytravellers.books.repositories.BookRepository;
import com.literarytravellers.books.services.BookService;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        Book existingBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + book.getId()));
        existingBook.setTitle(book.getTitle());
        existingBook.setDescription(book.getDescription());
        existingBook.setAuthors(book.getAuthors());
        existingBook.setCategories(book.getCategories());
        return bookRepository.save(existingBook);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
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
        bookRepository.deleteById(id);
    }

}

