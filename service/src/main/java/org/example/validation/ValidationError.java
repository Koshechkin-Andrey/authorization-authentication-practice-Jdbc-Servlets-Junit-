package org.example.validation;

import lombok.Value;

@Value(staticConstructor = "of")

public class ValidationError {
    String message;
}
