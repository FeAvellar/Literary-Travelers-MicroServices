package com.literarytravellers.books.validation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


import com.literarytravellers.books.entities.Author;
import com.literarytravellers.books.entities.Book;
import com.literarytravellers.books.entities.Category;
import com.literarytravellers.books.entities.Edition;
import com.literarytravellers.books.exceptions.ApplicationException;

@Component
public class BookValidator {

    @Autowired
    private EditionValidator editionValidator;

    public void validate(Book book) {
        validateTitle(book.getTitle());
        validateAuthorsList(book.getAuthors());
        validateCategoryList(book.getCategories());
        validateEditions(book.getEdition());
    }

    public void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "O título do livro é obrigatório.");
        }
    }

    public void validateAuthorsList(List<Author> authors) {
        if (authors == null || authors.isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_ACCEPTABLE, "Pelo menos um autor deve ser informado.");
        }
    }

    public void validateCategoryList(List<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_ACCEPTABLE, "Pelo menos uma categoria deve ser informada.");
        }
    }

    public void validateEditions(List<Edition> editions) {
        if (editions == null || editions.isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_ACCEPTABLE, "O livro deve ter pelo menos uma edição associada.");
        }

        // Delegar a validação de cada edição ao EditionValidator
        for (Edition edition : editions) {
            editionValidator.validate(edition);
        }
    }
}
