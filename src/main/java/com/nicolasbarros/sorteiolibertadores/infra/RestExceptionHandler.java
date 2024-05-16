package com.nicolasbarros.sorteiolibertadores.infra;

import com.nicolasbarros.sorteiolibertadores.exceptions.ResourceNotFoundException;
import com.nicolasbarros.sorteiolibertadores.exceptions.TeamNotDrawnNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<RestErrorMessage> teamNotDrawnNotFoundErrorHandler(ResourceNotFoundException exception) {
        RestErrorMessage threatError = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatError);
    }

    @ExceptionHandler(TeamNotDrawnNotFoundException.class)
    private ResponseEntity<RestErrorMessage> teamNotDrawnNotFoundErrorHandler(TeamNotDrawnNotFoundException exception) {
        RestErrorMessage threatError = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatError);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<RestErrorMessage> runtimeErrorHandler(RuntimeException exception) {
        RestErrorMessage threatError = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatError);
    }
}
