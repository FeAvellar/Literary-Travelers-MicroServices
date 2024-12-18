package com.literarytravellers.books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manipulador global de exceções para o microsserviço.
 * Centraliza o tratamento de exceções.
 * Garante que mensagens de erro claras e consistentes sejam retornadas ao cliente.
 * Captura tanto exceções personalizadas quanto genéricas.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções do tipo ApplicationException.
     *
     * @param ex Exceção gerada na aplicação.
     * @return ResponseEntity com mensagem e status HTTP.
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handleApplicationException(ApplicationException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    /**
     * Trata exceções genéricas.
     *
     * @param ex Exceção gerada.
     * @return ResponseEntity com mensagem e status HTTP.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno no servidor: " + ex.getMessage());
    }
}
