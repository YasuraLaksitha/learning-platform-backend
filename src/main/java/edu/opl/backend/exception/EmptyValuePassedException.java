package edu.opl.backend.exception;

public class EmptyValuePassedException extends RuntimeException {
    public EmptyValuePassedException(String message) {
        super(message);
    }
}
