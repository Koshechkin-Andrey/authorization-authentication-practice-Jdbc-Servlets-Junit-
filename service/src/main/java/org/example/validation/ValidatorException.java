package org.example.validation;

import lombok.Getter;

import java.util.List;

public class ValidatorException extends RuntimeException{

    @Getter
    private final List<ValidationError> errorList;

    public ValidatorException(List<ValidationError> errorList) {
        this.errorList = errorList;
    }
}
