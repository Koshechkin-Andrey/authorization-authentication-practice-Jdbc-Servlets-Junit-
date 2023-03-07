package org.example.validation;

public interface ValidatorExample<E> {
    ValidationResult isValid(E obj);
}
