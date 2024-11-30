package com.literarytravellers.books.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.literarytravellers.books.entities.Book;
import com.literarytravellers.books.entities.Category;
import com.literarytravellers.books.repositories.CategoryRepository;
import com.literarytravellers.books.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }    

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }   

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }   

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Book> getBooksByCategory(String categoryName) {
    List<Category> categories = categoryRepository.findByNameContainingIgnoreCase(categoryName);
    List<Book> books = new ArrayList<>();
    for (Category category : categories) {
        books.addAll(category.getBooks());
    }
    return books;
}  
}
