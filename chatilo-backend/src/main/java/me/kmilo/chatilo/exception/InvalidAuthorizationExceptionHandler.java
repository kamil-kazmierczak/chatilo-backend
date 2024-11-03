package me.kmilo.chatilo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidAuthorizationExceptionHandler {

    @ExceptionHandler(InvalidAuthorizationException.class)
    public ResponseEntity<InvalidAuthorizationErrorResponse> handle(InvalidAuthorizationException e) {
        InvalidAuthorizationErrorResponse response = new InvalidAuthorizationErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}