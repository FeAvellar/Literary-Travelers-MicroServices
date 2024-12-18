package com.literarytravellers.books.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Classe personalizada para exceções na aplicação.
 * 
 * Representa erros específicos no domínio do microsserviço, permitindo associar uma mensagem de erro e um código de status HTTP.
 * Flexível para ser usada em diversas partes da lógica de negócio.
 */
public class ApplicationException extends RuntimeException {
    private final HttpStatus status;

    /**
     * Construtor da exceção com mensagem e status HTTP.
     *
     * @param status  Status HTTP associado à exceção.
     * @param message Mensagem detalhada do erro.
     */
    public ApplicationException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * Obtém o status HTTP associado à exceção.
     *
     * @return Status HTTP.
     */
    public HttpStatus getStatus() {
        return status;
    }
}