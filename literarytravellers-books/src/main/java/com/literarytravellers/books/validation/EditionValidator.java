package com.literarytravellers.books.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.literarytravellers.books.entities.Edition;
import com.literarytravellers.books.exceptions.ApplicationException;
import com.literarytravellers.books.repositories.EditionRepository;

@Component
public class EditionValidator {

    @Autowired
    private EditionRepository editionRepository;

    public void validate(Edition edition) {
        validateEdition(edition.getEdition());
        validatePublisher(edition.getPublisher());
        validateISBN(edition.getIsbn10(), edition.getIsbn13());
    }

    public void validateEdition(String edition) {
        if (edition == null || edition.trim().isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_ACCEPTABLE, "O número da edição é obrigatório.");
        }
    }

    public void validatePublisher(String publisher) {
        if (publisher == null || publisher.trim().isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_ACCEPTABLE, "O nome da editora é obrigatório.");
        }

        if (publisher.length() > 255) {
            throw new ApplicationException(HttpStatus.NOT_ACCEPTABLE, "O nome da editora deve ter no máximo 255 caracteres.");
        }
    }

    public void validateISBN(String isbn10, String isbn13) {
        if ((isbn10 == null || isbn10.isEmpty()) && (isbn13 == null || isbn13.isEmpty())) {
            throw new ApplicationException(HttpStatus.NOT_ACCEPTABLE, "Pelo menos um ISBN (ISBN-10 ou ISBN-13) deve ser informado.");
        }

        if (isbn10 != null && !isbn10.matches("\\d{10}")) {
            throw new ApplicationException(HttpStatus.NOT_ACCEPTABLE, "ISBN-10 deve conter exatamente 10 dígitos.");
        }

        if (isbn13 != null && !isbn13.matches("\\d{13}")) {
            throw new ApplicationException(HttpStatus.NOT_ACCEPTABLE, "ISBN-13 deve conter exatamente 13 dígitos.");
        }
    }
}
