package com.animesh.employee.database.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public EntityNotFoundException(String message) {
        this.message = message;
    }

}
