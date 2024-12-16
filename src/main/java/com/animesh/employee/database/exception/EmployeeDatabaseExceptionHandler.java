package com.animesh.employee.database.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class EmployeeDatabaseExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(EntityNotFoundException ex, WebRequest request) {


        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, ex.getHttpStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> exceptionHandlerBadRequest(BadRequestException ex, WebRequest request) {


        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, ex.getHttpStatus());
    }
}
