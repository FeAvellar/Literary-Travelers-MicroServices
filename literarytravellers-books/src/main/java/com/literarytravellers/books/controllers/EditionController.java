package com.literarytravellers.books.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.literarytravellers.books.entities.Edition;
import com.literarytravellers.books.services.EditionService;

@RestController
@RequestMapping("/editions")
public class EditionController {

    @Autowired
    private EditionService editionService;

    @PostMapping
    public ResponseEntity<Edition> createEdition(@RequestBody Edition edition) {
        return ResponseEntity.ok(editionService.createEdition(edition));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Edition> getEditionById(@PathVariable Long id) {
        return ResponseEntity.ok(editionService.getEditionById(id));
    }

    @GetMapping
    public ResponseEntity<List<Edition>> getAllEditions() {
        return ResponseEntity.ok(editionService.getAllEditions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Edition> updateEdition(@PathVariable Long id, @RequestBody Edition edition) {
        return ResponseEntity.ok(editionService.updateEdition(id, edition));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEdition(@PathVariable Long id) {
        editionService.deleteEdition(id);
        return ResponseEntity.noContent().build();
    }
}
