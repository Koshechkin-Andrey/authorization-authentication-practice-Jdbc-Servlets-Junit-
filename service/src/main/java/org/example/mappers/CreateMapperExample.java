package org.example.mappers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.Country;
import org.example.entity.Gender;
import org.example.entity.UserEntity;

import java.time.LocalDate;

public class CreateMapperExample implements MapperExample<UserDto, UserEntity> {

    @Override
    public UserEntity transfer(UserDto obj) {
        return UserEntity.builder()
                .username(obj.getUsername())
                .birthday(LocalDate.parse(obj.getBirthday()))
                .email(obj.getEmail())
                .password(obj.getPassword())
                .gender(Gender.valueOf(obj.getGender()))
                .country(Country.valueOf(obj.getCountry()))
                .build();
    }
}
