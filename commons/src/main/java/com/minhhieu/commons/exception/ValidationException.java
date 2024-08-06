package com.minhhieu.commons.exception;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    List<ObjectError> errors;

    public ValidationException(String message, List<ObjectError> errors) {
        super(message);
        this.errors = errors;
    }
}
