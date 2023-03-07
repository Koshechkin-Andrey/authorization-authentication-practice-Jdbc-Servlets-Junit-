package org.example.validation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
@Getter
    private final List<ValidationError> errorList = new ArrayList<>();

    public void addError(ValidationError validationError) {
        this.errorList.add(validationError);
    }

    public boolean isValid(){
        return errorList.isEmpty();
    }
}
