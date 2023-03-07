package org.example.validation;

import org.assertj.core.api.Assertions;
import org.example.dto.UserDto;
import org.example.entity.Country;
import org.example.entity.Gender;
import org.example.entity.UserEntity;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("ValidatorUnitTest")
@DisplayName("Validator unit test")
class ValidationResultTest {

    private RegistrationValidator registrationValidator;

    @BeforeEach
    void init() {
        this.registrationValidator = new RegistrationValidator();
    }

    @Test
    void userSuccessIfIntroducedDateCorrectly(){
        //Given
        UserDto userDto = UserDto.builder()
                .username("username")
                .email("username@gmail.com")
                .password("123")
                .country(Country.Russia.name())
                .birthday(LocalDate.of(2000, 1, 1).toString())
                .gender(Gender.MALE.name())
                .build();

        //When
        ValidationResult validatorValid = registrationValidator.isValid(userDto);

        //Then
        Assertions.assertThat(validatorValid.isValid()).isTrue();
        assertTrue(validatorValid.getErrorList().isEmpty());
    }

    @Test
    void ifUsernameIntroducedIsNotCorrectlyShouldMessage(){
        //Given
        UserDto userDto = UserDto.builder()
                .username("")
                .email("username@gmail.com")
                .password("123")
                .country(Country.Russia.name())
                .birthday(LocalDate.of(2000, 1, 1).toString())
                .gender(Gender.MALE.name())
                .build();

        //When
        ValidationResult validatorValid = registrationValidator.isValid(userDto);
        List<ValidationError> actualResult = validatorValid.getErrorList();

        //Then
        Assertions.assertThat(actualResult).hasSize(1);
        Assertions.assertThat(actualResult.get(0).getMessage()).contains("Username is empty");

    }
    @Test
    void ifEmailIntroducedIsNotCorrectlyShouldMessage() {
        //Given
        UserDto userDto = UserDto.builder()
                .username("username")
                .email("")
                .password("123")
                .country(Country.Russia.name())
                .birthday(LocalDate.of(2000, 1, 1).toString())
                .gender(Gender.MALE.name())
                .build();

        //When
        ValidationResult validatorValid = registrationValidator.isValid(userDto);
        List<ValidationError> actualResult = validatorValid.getErrorList();

        //Then
        Assertions.assertThat(actualResult).hasSize(1);
        Assertions.assertThat(actualResult.get(0).getMessage()).contains("Email is empty");

    }

    @Test
    void ifPasswordIntroducedIsNotCorrectlyShouldMessage() {
        //Given
        UserDto userDto = UserDto.builder()
                .username("username")
                .email("username@gmail.com")
                .password("")
                .country(Country.Russia.name())
                .birthday(LocalDate.of(2000, 1, 1).toString())
                .gender(Gender.MALE.name())
                .build();

        //When
        ValidationResult validatorValid = registrationValidator.isValid(userDto);
        List<ValidationError> actualResult = validatorValid.getErrorList();

        //Then
        Assertions.assertThat(actualResult).hasSize(1);
        Assertions.assertThat(actualResult.get(0).getMessage()).contains("Password is empty");
    }

    @Test
    void ifEmailAndPasswordIntroducedIsNotCorrectlyShouldMessage() {
        //Given
        UserDto userDto = UserDto.builder()
                .username("username")
                .email("")
                .password("")
                .country(Country.Russia.name())
                .birthday(LocalDate.of(2000, 1, 1).toString())
                .gender(Gender.MALE.name())
                .build();

        //When
        ValidationResult validatorValid = registrationValidator.isValid(userDto);
        List<ValidationError> actualResult = validatorValid.getErrorList();
        List<String> listError = actualResult.stream().map(ValidationError::getMessage).toList();

        //Then
        Assertions.assertThat(listError).contains("Email is empty", "Password is empty");
    }

    @Test
    void ifGenderIntroducedIsNotCorrectlyShouldMessage() {
        //Given
        UserDto userDto = UserDto.builder()
                .username("username")
                .email("username@gmail.com")
                .password("123")
                .country(Country.Russia.name())
                .birthday(LocalDate.of(2000, 1, 1).toString())
                .gender("")
                .build();

        //When
        ValidationResult validatorValid = registrationValidator.isValid(userDto);
        List<ValidationError> actualResult = validatorValid.getErrorList();

        //Then
        Assertions.assertThat(actualResult).hasSize(1);
        Assertions.assertThat(actualResult.get(0).getMessage()).contains("Gender invalid");
    }

    @Test
    void ifCountyIntroducedIsNotCorrectlyShouldMessage() {
        //Given
        UserDto userDto = UserDto.builder()
                .username("username")
                .email("username@gmail.com")
                .password("123")
                .country("")
                .birthday(LocalDate.of(2000, 1, 1).toString())
                .gender(Gender.MALE.name())
                .build();

        //When
        ValidationResult validatorValid = registrationValidator.isValid(userDto);
        List<ValidationError> actualResult = validatorValid.getErrorList();

        //Then
        Assertions.assertThat(actualResult).hasSize(1);
        Assertions.assertThat(actualResult.get(0).getMessage()).contains("Country invalid");
    }

}