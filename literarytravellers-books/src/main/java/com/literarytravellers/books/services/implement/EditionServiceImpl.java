package com.literarytravellers.books.services.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.literarytravellers.books.entities.Edition;
import com.literarytravellers.books.repositories.EditionRepository;
import com.literarytravellers.books.services.EditionService;

@Service
public class EditionServiceImpl implements EditionService {
    private final EditionRepository editionRepository;

    public EditionServiceImpl(EditionRepository editionRepository) {
        this.editionRepository = editionRepository;
    }

    @Override
    public Edition createEdition(Edition edition) {
        // Validações antes de salvar a edição
        validateISBN(edition.getIsbn10(), edition.getIsbn13());
       
        // Exemplo: Verificar se já existe uma edição com o mesmo ISBN
    if (editionRepository.existsByIsbn10OrIsbn13(edition.getIsbn10(), edition.getIsbn13())) {
        throw new RuntimeException("Edition with the same ISBN already exists.");
    }

    return editionRepository.save(edition);
}
    
    @Override
   public Edition updateEdition(Long id, Edition edition) {
    // Verifica se a edição existe
    Edition existingEdition = editionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Edition not found with id: " + id));

        // Atualiza os campos permitidos
        existingEdition.setEdition(edition.getEdition());
        existingEdition.setPublisher(edition.getPublisher());
        existingEdition.setIsbn10(edition.getIsbn10());
        existingEdition.setIsbn13(edition.getIsbn13());
        existingEdition.setPages(edition.getPages());
        existingEdition.setReleaseDate(edition.getReleaseDate());
        existingEdition.setCoverPhoto(edition.getCoverPhoto());

        return editionRepository.save(existingEdition);
    }

    @Override
    public Edition getEditionById(Long id) {
        return editionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Edition not found with id: " + id));
    }

    @Override
    public List<Edition> getAllEditions() {
        return editionRepository.findAll();
    }

    @Override
    public void deleteEdition(Long id) {
        if (!editionRepository.existsById(id)) {
            throw new RuntimeException("Edition not found with id: " + id);
        }
        editionRepository.deleteById(id);
    }

    @Override
    public List<Edition> getEditionsByBook(Long bookId) {
        return editionRepository.findByBookId(bookId);
    }

    /**
     * Valida os números de ISBN.
     *
     * @param isbn10 ISBN-10 da edição.
     * @param isbn13 ISBN-13 da edição.
     */
    private void validateISBN(String isbn10, String isbn13) {
        if ((isbn10 == null || isbn10.isEmpty()) && (isbn13 == null || isbn13.isEmpty())) {
            throw new RuntimeException("At least one valid ISBN (10 or 13) must be provided.");
        }
        if (isbn10 != null && !isbn10.matches("\\d{10}")) {
            throw new RuntimeException("Invalid ISBN-10 format. It must be exactly 10 digits.");
        }
        if (isbn13 != null && !isbn13.matches("\\d{13}")) {
            throw new RuntimeException("Invalid ISBN-13 format. It must be exactly 13 digits.");
        }
    }
}
    

