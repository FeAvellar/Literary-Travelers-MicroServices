
package com.literarytravellers.books.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.literarytravellers.books.entities.Author;
import com.literarytravellers.books.repositories.AuthorRepository;
import com.literarytravellers.books.services.AuthorService;

/**
 * Implementação da interface de serviço de autores.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    /**
     * Instância do repositório de autores.
     */
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Retorna uma lista com todos os autores cadastrados.
     * 
     * @return lista de autores.
     */
    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            throw new RuntimeException("Não há autores cadastrados");
        }
        return authors;
    }

    /**
     * Cria um novo autor.
     * 
     * @param author autor a ser criado.
     * @return autor criado.
     */
    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    /**
     * Atualiza um autor.
     * 
     * @param author autor a ser atualizado.
     * @return autor atualizado.
     */
    @Override
    public Author updateAuthor(Long id, Author author) {
        return authorRepository.save(author);
    }

    /**
     * Obtém um autor pelo seu ID.
     * 
     * @param id ID do autor.
     * @return autor encontrado.
     */
    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    /**
     * Exclui um autor pelo seu ID.
     * 
     * @param id ID do autor.
     */
    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    /**
     * Obtém uma lista de autores pelo nome.
     * 
     * @param name nome do autor.
     * @return lista de autores.
     */
    @Override
    public List<Author> getAuthorsByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Obtém uma lista de autores pelo título do livro.
     * 
     * @param bookTitle título do livro.
     * @return lista de autores.
     */
    @Override
    public List<Author> getAuthorsByBookTitle(String bookTitle) {
        return authorRepository.findByBooksTitle(bookTitle);
    }

    /**
     * Obtém uma lista de autores pela editora.
     * 
     * @param publisher editora.
     * @return lista de autores.
     */
    @Override
    public List<Author> getAuthorsByPublisher(String publisher) {
        return authorRepository.findByPublisherContainingIgnoreCase(publisher);
    }

    /**
     * Obtém uma lista de autores pela categoria.
     * 
     * @param category categoria.
     * @return lista de autores.
     */
    @Override
    public List<Author> getAuthorsByCategory(String category) {
        return authorRepository.findByCategoriesNameContainingIgnoreCase(category);
    }

}
