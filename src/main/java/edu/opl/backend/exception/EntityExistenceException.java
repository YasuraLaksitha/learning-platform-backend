package edu.opl.backend.exception;

public class EntityExistenceException extends RuntimeException {
    public EntityExistenceException(String message) {
        super(message);
    }
}
