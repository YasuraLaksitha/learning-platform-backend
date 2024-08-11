package edu.opl.backend.exception;

import edu.opl.backend.dto.ErrorResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EmptyValuePassedException.class)
    ResponseEntity<ErrorResponseBody> handleEmptyValuePassedException(EmptyValuePassedException ex){
        final ErrorResponseBody responseBody = ErrorResponseBody.builder()
                .message(ex.getMessage()).status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok().body(responseBody);
    }

    @ExceptionHandler(value = IllegalValuePassedException.class)
    ResponseEntity<ErrorResponseBody> handleIllegalValuePassedException(IllegalValuePassedException ex){
        final ErrorResponseBody responseBody = ErrorResponseBody.builder()
                .message(ex.getMessage()).status(HttpStatus.NOT_ACCEPTABLE).build();
        return ResponseEntity.ok().body(responseBody);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    ResponseEntity<ErrorResponseBody> handleEntityNotFoundException(EntityNotFoundException ex){
        final ErrorResponseBody responseBody = ErrorResponseBody.builder()
                .message(ex.getMessage()).status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok().body(responseBody);
    }

    @ExceptionHandler(value = EntityExistenceException.class)
    ResponseEntity<ErrorResponseBody> handleEntityExistenceException(EntityExistenceException ex){
        final ErrorResponseBody responseBody = ErrorResponseBody.builder()
                .message(ex.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok().body(responseBody);
    }

    @ExceptionHandler(value = IncorrectPasswordException.class)
    ResponseEntity<ErrorResponseBody> handleIncorrectPasswordException(IncorrectPasswordException ex) {
        final ErrorResponseBody responseBody = ErrorResponseBody.builder()
                .message(ex.getMessage()).status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok().body(responseBody);
    }
}
