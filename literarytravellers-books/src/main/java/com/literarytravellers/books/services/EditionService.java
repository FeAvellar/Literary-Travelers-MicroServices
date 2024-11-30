package com.literarytravellers.books.services;

import java.util.List;

import com.literarytravellers.books.entities.Edition;

public interface EditionService {

    
    Edition createEdition(Edition edition);
    Edition updateEdition(Long id, Edition edition);
    Edition getEditionById(Long id);
    List<Edition> getAllEditions();
    List<Edition> getEditionsByBook(Long bookId);
    void deleteEdition(Long id);
    
}
