package com.app.blockbuster.exceptionhandler;

import com.app.blockbuster.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(UserAlreadyPresentException.class)
    public ResponseEntity<ErrorMessage> userAlreadyPresent(UserAlreadyPresentException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().
                statusCode(HttpStatus.BAD_REQUEST.value()).
                timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorMessage> movieNotFound(MovieNotFoundException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().
                statusCode(HttpStatus.BAD_REQUEST.value()).
                timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovieAlreadyPresentException.class)
    public ResponseEntity<ErrorMessage> movieFound(MovieAlreadyPresentException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().
                statusCode(HttpStatus.BAD_REQUEST.value()).
                timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotIssueSameMovieException.class)
    public ResponseEntity<ErrorMessage> cannotIssueSameMovie(CannotIssueSameMovieException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().
                statusCode(HttpStatus.BAD_REQUEST.value()).
                timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLoginCredentialsException.class)
    public ResponseEntity<ErrorMessage> invalidUserCredentials(InvalidLoginCredentialsException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().
                statusCode(HttpStatus.UNAUTHORIZED.value()).
                timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    public ResponseEntity<ErrorMessage> invalidAuthentication(InvalidAuthenticationException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().
                statusCode(HttpStatus.UNAUTHORIZED.value()).
                timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorMessage> tokenExpired(ExpiredJwtException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().
                statusCode(HttpStatus.UNAUTHORIZED.value()).
                timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotAuthorisedException.class)
    public ResponseEntity<ErrorMessage> unauthrized(UserNotAuthorisedException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().
                statusCode(HttpStatus.UNAUTHORIZED.value()).
                timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InventoryUnavailableException.class)
    public ResponseEntity<ErrorMessage> inventoryUnavailable(InventoryUnavailableException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().
                statusCode(HttpStatus.NOT_FOUND.value()).
                timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
