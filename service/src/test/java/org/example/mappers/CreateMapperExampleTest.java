package org.example.mappers;

import org.assertj.core.api.Assertions;
import org.example.dto.UserDto;
import org.example.entity.Country;
import org.example.entity.Gender;
import org.example.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Tag("CreateMapperTest")
@DisplayName("Create mapper unit test")
class CreateMapperExampleTest {

    private CreateMapperExample createMapperExample;


    @BeforeEach
    void init(){
        this.createMapperExample = new CreateMapperExample();
    }

    @Test
    void shouldReturnEntityIfInParametersDto() {
        UserDto userDto = getUserDto("username@gmail.com");
        UserEntity userEntity = getUserEntity("username@gmail.com");

        UserEntity actualResult = createMapperExample.transfer(userDto);

        Assertions.assertThat(actualResult).isEqualTo(userEntity);
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