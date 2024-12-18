package com.literarytravellers.books.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.literarytravellers.books.entities.Category;
import com.literarytravellers.books.exceptions.ApplicationException;
import com.literarytravellers.books.repositories.CategoryRepository;

@Component
public class CategoryValidator {

    @Autowired
    private CategoryRepository categoryRepository;

    public void validate(Category category) {
        validateName(category.getName());
    }

    public void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_ACCEPTABLE, "O nome da categoria é obrigatório.");
        }
        if (!categoryRepository.findByNameContainingIgnoreCase(name).isEmpty()) {
            throw new ApplicationException(HttpStatus.CONFLICT,"Já existe uma categoria com este nome.");
        }
    }
}
