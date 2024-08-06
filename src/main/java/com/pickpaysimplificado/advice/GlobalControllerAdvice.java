package com.pickpaysimplificado.advice;

import com.pickpaysimplificado.dto.ExceptionDTO;
import com.pickpaysimplificado.exceptions.FailedException;
import com.pickpaysimplificado.exceptions.NotFoundException;
import com.pickpaysimplificado.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Global controller advice.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * Handle not found response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleNotFound(NotFoundException exception) {
        var exceptionResponse = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    /**
     * Handle unauthorized response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionDTO> handleUnauthorized(UnauthorizedException exception) {
        var exceptionResponse = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(exceptionResponse);
    }

    /**
     * Handle unauthorized response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(FailedException.class)
    public ResponseEntity<ExceptionDTO> handleFailed(FailedException exception) {
        var exceptionResponse = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponse);
    }
}