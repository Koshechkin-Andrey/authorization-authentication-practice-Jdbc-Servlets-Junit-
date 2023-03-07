package org.example.mappers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.UserEntity;


public class ReadMapperExample implements MapperExample<UserEntity, UserDto>{


    @Override
    public UserDto transfer(UserEntity obj) {
        return UserDto.builder()
                .username(obj.getUsername())
                .birthday(obj.getBirthday().toString())
                .email(obj.getEmail())
                .password(obj.getPassword())
                .gender(obj.getGender().name())
                .country(obj.getCountry().name())
                .build();
    }
}
