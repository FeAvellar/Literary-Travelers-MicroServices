 
package com.literarytravelers.user.exceptions;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final HttpStatus statusCode;

    public ApplicationException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ApplicationException(String message, HttpStatus statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return super.toString() + (getCause() != null ? ": " + getCause().toString() : "");
    }

    public String getStackTraceString() {
        StringBuilder stackTraceBuilder = new StringBuilder();
        stackTraceBuilder.append(this).append("\n");
        for (StackTraceElement element : getStackTrace()) {
            stackTraceBuilder.append("\tat ").append(element).append("\n");
        }
        Throwable cause = getCause();
        if (cause != null) {
            stackTraceBuilder.append("Caused by: ").append(cause).append("\n");
            for (StackTraceElement element : cause.getStackTrace()) {
                stackTraceBuilder.append("\tat ").append(element).append("\n");
            }
        }
        return stackTraceBuilder.toString();
    }

    public static class LoginNameAlreadyExists extends ApplicationException {
        private static final long serialVersionUID = 1L;

        public LoginNameAlreadyExists(String message) {
            super(message, HttpStatus.CONFLICT);
        }

        public LoginNameAlreadyExists(String message, Throwable cause) {
            super(message, HttpStatus.CONFLICT, cause);
        }

        public LoginNameAlreadyExists(String message, HttpStatus status) {
            super(message, status);
        }

        public LoginNameAlreadyExists(String message, HttpStatus status, Throwable cause) {
            super(message, status, cause);
        }
    }

    public static class EmailAlreadyExists extends ApplicationException {
        private static final long serialVersionUID = 1L;

        public EmailAlreadyExists(String message) {
            super(message, HttpStatus.CONFLICT);
        }

        public EmailAlreadyExists(String message, Throwable cause) {
            super(message, HttpStatus.CONFLICT, cause);
        }

        public EmailAlreadyExists(String message, HttpStatus status) {
            super(message, status);
        }

        public EmailAlreadyExists(String message, HttpStatus status, Throwable cause) {
            super(message, status, cause);
        }
    }

    public static class RoleNameAlreadyExists extends ApplicationException {
        private static final long serialVersionUID = 1L;

        public RoleNameAlreadyExists(String message) {
            super(message, HttpStatus.CONFLICT);
        }

        public RoleNameAlreadyExists(String message, Throwable cause) {
            super(message, HttpStatus.CONFLICT, cause);
        }

        public RoleNameAlreadyExists(String message, HttpStatus status) {
            super(message, status);
        }

        public RoleNameAlreadyExists(String message, HttpStatus status, Throwable cause) {
            super(message, status, cause);
        }
    }

    public static class PhonesAlreadyExists extends ApplicationException {
        private static final long serialVersionUID = 1L;

        public PhonesAlreadyExists(String message) {
            super(message, HttpStatus.CONFLICT);
        }

        public PhonesAlreadyExists(String message, Throwable cause) {
            super(message, HttpStatus.CONFLICT, cause);
        }

        public PhonesAlreadyExists(String message, HttpStatus status) {
            super(message, status);
        }

        public PhonesAlreadyExists(String message, HttpStatus status, Throwable cause) {
            super(message, status, cause);
        }
    }
}