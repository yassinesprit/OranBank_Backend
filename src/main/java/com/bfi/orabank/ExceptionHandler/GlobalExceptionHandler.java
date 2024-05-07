package com.bfi.orabank.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "An error occurred while processing the request  "+ex.getMessage();

        if (ex instanceof RuntimeException
                || ex instanceof ExpiredJwtException) {
            status = HttpStatus.BAD_REQUEST;
            message = ex.getMessage();
        }
        if(ex instanceof NotFoundException)
        {
            status = HttpStatus.NOT_FOUND;
            message = ex.getMessage();
        }


        return handleExceptionInternal(ex, message, new HttpHeaders(), status, request);
    }

}
