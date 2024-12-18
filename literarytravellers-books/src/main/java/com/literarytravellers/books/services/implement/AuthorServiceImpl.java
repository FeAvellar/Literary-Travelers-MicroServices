package com.literarytravellers.books.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.literarytravellers.books.entities.Author;
import com.literarytravellers.books.exceptions.ApplicationException;
import com.literarytravellers.books.repositories.AuthorRepository;
import com.literarytravellers.books.services.AuthorService;
import com.literarytravellers.books.validation.BookValidator;

/**
 * Implementação da interface de serviço de autores.
 */
/**
 * Implementação da interface de serviço de autores.
 * 
 * Essa classe fornece implementações para os métodos de serviço
 * de autores, como a listagem de todos, criação, atualização e
 * exclusão de autores, bem como a busca por autores com base em
 * nome, tı́tulo de livro, editora e categoria.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookValidator bookValidator;

    /**
     * Retorna uma lista com todos os autores.
     * 
     * @return lista com todos os autores.
     * @throws ApplicationException se nenhuma autor for encontrado.
     */
    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "Nenhum autor encontrado.");
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
     * Atualiza um autor existente.
     * 
     * @param id    ID do autor a ser atualizado.
     * @param author autor com os dados atualizados.
     * @return autor atualizado.
     * @throws ApplicationException se o autor nenhuma autor for encontrado com o ID
     *                              especificado.
     */
    @Override
    public Author updateAuthor(Long id, Author author) {
        // Validação para garantir que o autor existe antes de atualizar
        if (!authorRepository.existsById(id)) {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "Autor não encontrado com o ID: " + id);
        }
        author.setId(id); // Garante que o ID fornecido seja usado na atualização
        return authorRepository.save(author);
    }

    /**
     * Retorna um autor com base em seu ID.
     * 
     * @param id ID do autor a ser encontrado.
     * @return autor encontrado.
     * @throws ApplicationException se nenhuma autor for encontrado com o ID especificado.
     */
    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND, "Autor não encontrado com o ID: " + id));
    }

    /**
     * Exclui um autor com base em seu ID.
     * 
     * @param id ID do autor a ser excluído.
     * @throws ApplicationException se nenhuma autor for encontrado com o ID especificado.
     */
    @Override
    public void deleteAuthorById(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "Autor não encontrado com o ID: " + id);
        }
        authorRepository.deleteById(id);
    }

    /**
     * Retorna uma lista de autores com base em seu nome.
     * 
     * @param name nome do autor.
     * @return lista de autores com o nome especificado.
     * @throws ApplicationException se nenhuma autor for encontrado com o nome especificado.
     */
    @Override
    public List<Author> getAuthorsByName(String name) {
        List<Author> authors = authorRepository.findByNameContainingIgnoreCase(name);
        if (authors.isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "Nenhum autor encontrado com o nome: " + name);
        }
        return authors;
    }

    /**
     * Retorna uma lista de autores com base em um tıtulo de livro.
     * 
     * @param bookTitle tıtulo do livro.
     * @return lista de autores com o tıtulo do livro especificado.
     * @throws ApplicationException se nenhuma autor for encontrado com o tıtulo especificado.
     */
    @Override
    public List<Author> getAuthorsByBookTitle(String bookTitle) {
        List<Author> authors = authorRepository.findByBooksTitle(bookTitle);
        if (authors.isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "Nenhum autor encontrado com o título do livro: " + bookTitle);
        }
        return authors;
    }

    /**
     * Retorna uma lista de autores com base em uma editora.
     * 
     * @param publisher nome da editora.
     * @return lista de autores com a editora especificada.
     * @throws ApplicationException se nenhuma autor for encontrado com a editora especificada.
     */
    @Override
    public List<Author> getAuthorsByPublisher(String publisher) {
        return authorRepository.findAuthorsByPublisher(publisher);
    }

    /**
     * Retorna uma lista de autores com base em uma categoria.
     * 
     * @param category nome da categoria.
     * @return lista de autores com a categoria especificada.
     * @throws ApplicationException se nenhuma autor for encontrado com a categoria especificada.
     */
    @Override
    public List<Author> getAuthorsByCategory(String category) {
        List<Author> authors = authorRepository.findByCategoryNameContainingIgnoreCase(category);
        if (authors.isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "Nenhum autor encontrado para a categoria: " + category);
        }
        return authors;
    }
}
