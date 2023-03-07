package org.example.service;

import net.bytebuddy.asm.Advice;
import org.assertj.core.api.Assertions;
import org.example.dao.UserDao;
import org.example.dto.UserDto;
import org.example.entity.Country;
import org.example.entity.Gender;
import org.example.entity.UserEntity;
import org.example.mappers.CreateMapperExample;
import org.example.mappers.ReadMapperExample;
import org.example.validation.RegistrationValidator;
import org.example.validation.ValidationError;
import org.example.validation.ValidationResult;
import org.example.validation.ValidatorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Tag("UserServiceTest")
@DisplayName("User service unit test")
class UserServiceTest {

    @Mock
    private CreateMapperExample createMapperExample;

    @Mock
    private ReadMapperExample readMapperExample;

    @Mock
    private UserDao userDao;

    @Mock
    private RegistrationValidator registrationValidator;

    @InjectMocks
    private UserService userService;


    @Test
    void findAll() {
        UserDto userDto1 = getUserDto("username1@gmail.com");
        UserDto userDto2 = getUserDto("username2@gmail.com");
        UserDto userDto3 = getUserDto("username3@gmail.com");


        UserEntity userEntity1 = getUserEntity("username1@gmail.com");
        UserEntity userEntity2 = getUserEntity("username2@gmail.com");
        UserEntity userEntity3 = getUserEntity("username3@gmail.com");
        List<UserEntity> entityList = List.of(userEntity1, userEntity2, userEntity3);

        doReturn(entityList).when(userDao).findAll();
        doReturn(userDto1).when(readMapperExample).transfer(userEntity1);
        doReturn(userDto2).when(readMapperExample).transfer(userEntity2);
        doReturn(userDto3).when(readMapperExample).transfer(userEntity3);


        List<UserDto> actualResult = userService.findAll();
        Assertions.assertThat(actualResult).isNotEmpty();
        Assertions.assertThat(actualResult).hasSize(3);
    }


    @Tag("createUser")
    @Nested
    @DisplayName("test create user")
    class createUser {

        @Test
        void createUserIfDateIsCorrected() {
            //Given
            UserDto userDto = getUserDto("username@gmail.com");
            UserEntity userEntity = getUserEntity("username@gmail.com");

            //When
            doReturn(new ValidationResult()).when(registrationValidator).isValid(userDto);
            doReturn(userEntity).when(createMapperExample).transfer(userDto);
            doReturn(userDto).when(readMapperExample).transfer(userEntity);
            UserDto actualResult = userService.createUser(userDto);

            //Then
            Assertions.assertThat(actualResult).isEqualTo(userDto);

        }

        @Test
        @DisplayName("Test throw ValidatorException")
        void shouldThrowValidationExceptionIfDateInNotCorrected() {
            //Given
            UserDto userDto = getUserDto("");

            //When
            ValidationResult valid = new ValidationResult();
            valid.addError(ValidationError.of("invalid.email"));
            doReturn(valid).when(registrationValidator).isValid(userDto);

            //Then
            assertThrows(ValidatorException.class, () -> userService.createUser(userDto));
            verifyNoInteractions(readMapperExample, userDao);


        }
    }


    @Tag("testLogin")
    @Nested
    @DisplayName("test login")
    class login {
        @Test
        void ifEmailAndPasswordEnterEnteredCorrectly() {
            //Given
            UserDto userDto = getUserDto("username@gmail.com");
            UserEntity userEntity = getUserEntity("username@gmail.com");

            doReturn(Optional.of(userEntity)).when(userDao).findUserByEmailAndPassword(userEntity.getEmail(), userEntity.getPassword());
            doReturn(userDto).when(readMapperExample).transfer(userEntity);

            //When
            Optional<UserDto> actualResult = userService.findUserByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
            Optional<String> actualResultEmail = actualResult.map(UserDto::getEmail);

            //Then
            Assertions.assertThat(actualResult).isPresent();
            Assertions.assertThat(actualResultEmail).contains("username@gmail.com");


        }

        @Test
        void ifUsernameOrPasswordIsNotEnteredCorrected() {
            //Given
            UserDto userDto = getUserDto("empty");
            UserEntity userEntity = getUserEntity("empty");

            //When
            doReturn(Optional.empty()).when(userDao).findUserByEmailAndPassword(userEntity.getEmail(), userEntity.getPassword());

            //Then
            Optional<UserDto> actualResult = userService.findUserByEmailAndPassword(userDto.getEmail(), userDto.getPassword());

            verifyNoInteractions(readMapperExample);
            Assertions.assertThat(actualResult).isEmpty();
        }

    }


    private static UserEntity getUserEntity(String email) {
        return UserEntity.builder()
                .username("username")
                .email(email)
                .password("123")
                .country(Country.Russia)
                .birthday(LocalDate.of(2000, 1, 1))
                .gender(Gender.MALE)
                .build();
    }

    private static UserDto getUserDto(String email) {
        return UserDto.builder()
                .username("username")
                .email(email)
                .password("123")
                .country(Country.Russia.name())
                .birthday(LocalDate.of(2000, 1, 1).toString())
                .gender(Gender.MALE.name())
                .build();
    }
}