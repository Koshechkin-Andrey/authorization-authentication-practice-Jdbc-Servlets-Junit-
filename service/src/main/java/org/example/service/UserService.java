package org.example.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.dao.UserDao;
import org.example.dto.UserDto;
import org.example.entity.UserEntity;
import org.example.mappers.CreateMapperExample;
import org.example.mappers.ReadMapperExample;
import org.example.validation.RegistrationValidator;
import org.example.validation.ValidationResult;
import org.example.validation.ValidatorException;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class UserService {


    private final ReadMapperExample readMapper;
    private final CreateMapperExample createMapper;
    private final RegistrationValidator registrationValidator;
    private final UserDao userDao;


    public List<UserDto> findAll() {

        return userDao.findAll().stream().map(readMapper::transfer).toList();
    }


    public UserDto createUser(UserDto userDto) {
        ValidationResult validationResult  = registrationValidator.isValid(userDto);

        if (!validationResult.isValid()) {
            throw new ValidatorException(validationResult.getErrorList());
        } else {
            UserEntity entity = createMapper.transfer(userDto);
            userDao.createUser(entity);
            return readMapper.transfer(entity);
        }
    }

    public Optional<UserDto> findUserByEmailAndPassword(String email, String password) {
        return userDao.findUserByEmailAndPassword(email, password).map(readMapper::transfer);
    }

}
