package org.example.dao;

import lombok.*;
import org.example.entity.Country;
import org.example.entity.Gender;
import org.example.entity.UserEntity;
import org.example.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static org.example.util.Query.*;


public class UserDao {


    @SneakyThrows
    public Optional<UserEntity> findUserByEmailAndPassword(String email, String password) {

        @Cleanup Connection connection = ConnectionManager.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD);

        preparedStatement.setObject(1, email);
        preparedStatement.setObject(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        UserEntity user = null;
        while (resultSet.next()) {
            user = getBuild(resultSet);
        }
        return Optional.ofNullable(user);
    }


    @SneakyThrows
    public List<UserEntity> findAll() {

        List<UserEntity> entityList = new ArrayList<>();

        @Cleanup Connection connection = ConnectionManager.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            entityList.add(getBuild(resultSet));
        }

        return entityList;
    }

    @SneakyThrows
    public UserEntity createUser(UserEntity user) {
        @Cleanup Connection connection = ConnectionManager.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, RETURN_GENERATED_KEYS);

        preparedStatement.setObject(1, user.getUsername());
        preparedStatement.setObject(2, user.getBirthday());
        preparedStatement.setObject(3, user.getEmail());
        preparedStatement.setObject(4, user.getPassword());
        preparedStatement.setObject(5, user.getGender().name());
        preparedStatement.setObject(6, user.getCountry().name());
        int result = preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        user.setId(generatedKeys.getObject("id", Long.class));
        return user;
    }

    private static UserEntity getBuild(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .id(resultSet.getObject("id", Long.class))
                .username(resultSet.getObject("username", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .gender(Gender.valueOf(resultSet.getObject("gender", String.class)))
                .country(Country.valueOf(resultSet.getObject("country", String.class)))
                .build();
    }

}
