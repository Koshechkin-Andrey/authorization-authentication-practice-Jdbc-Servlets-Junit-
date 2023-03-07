package org.example.validation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.Country;
import org.example.entity.Gender;


public class RegistrationValidator implements ValidatorExample<UserDto> {



    @Override
    public ValidationResult isValid(UserDto obj) {
        ValidationResult validationResult = new ValidationResult();

        if (obj.getUsername().isEmpty()) {
            validationResult.addError(ValidationError.of("Username is empty"));
        }
        if (obj.getEmail().isEmpty()) {
            validationResult.addError(ValidationError.of("Email is empty"));
        }
        if (obj.getPassword().isEmpty()) {
            validationResult.addError(ValidationError.of("Password is empty"));
        }
        if (Gender.findOpt(obj.getGender()).isEmpty()) {
            validationResult.addError(ValidationError.of("Gender invalid"));
        }
        if (Country.findOpt(obj.getCountry()).isEmpty()) {
            validationResult.addError(ValidationError.of("Country invalid"));
        }
        return validationResult;
    }
}
