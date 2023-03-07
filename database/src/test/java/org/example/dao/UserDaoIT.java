package org.example.dao;

import org.assertj.core.api.Assertions;
import org.example.entity.Country;
import org.example.entity.Gender;
import org.example.entity.UserEntity;
import org.example.util.H2dateBaseUtil;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDaoIT extends H2dateBaseUtil {

    private final UserDao userDao = new UserDao();


    @Test
    void findAll() {
        userDao.createUser(getUserEntity("username1@gmail.com", "username1"));
        userDao.createUser(getUserEntity("username2@gmail.com", "username2"));
        userDao.createUser(getUserEntity("username3@gmail.com", "username3"));

        List<UserEntity> actualResult = userDao.findAll();
        List<String> listUsername = actualResult.stream().map(UserEntity::getUsername).toList();

        Assertions.assertThat(actualResult).hasSize(3);
        Assertions.assertThat(listUsername).contains("username1", "username2", "username3");

    }


    @Tag("TestCreatUser")
    @DisplayName("IT test methods create user")
    @Nested
    class createUser{

        @Test
        void shouldCreateUserIfDateCorrectly(){
            UserEntity userEntity = getUserEntity("username@gmail.com", "username");
            UserEntity actualResult = userDao.createUser(userEntity);
            Assertions.assertThat(actualResult).isEqualTo(userEntity);
        }

        @Test
        void shouldReturnExceptionIfPasswordIsNull(){
            UserEntity userEntity = getUserEntity("username@gmail.com", null);
            assertThrows(JdbcSQLIntegrityConstraintViolationException.class,
                    ()-> userDao.createUser(userEntity));
        }

        @Test
        void shouldReturnExceptionIfUsernameIsNull(){
            UserEntity userEntity = getUserEntity(null, "username");
            assertThrows(JdbcSQLIntegrityConstraintViolationException.class,
                    ()-> userDao.createUser(userEntity));

        }

    }


    @Tag("TestLogin")
    @DisplayName("IT test methods login by username and password")
    @Nested
    class login{
        @Test
        void findUserByEmailAndPassword() {
            UserEntity userEntity = userDao.createUser(getUserEntity("usernam1@gmail.com", "username1"));
            Optional<UserEntity> actualResult = userDao.findUserByEmailAndPassword(userEntity.getEmail(), userEntity.getPassword());
            actualResult.isPresent();
            Assertions.assertThat(actualResult.get()).isEqualTo(userEntity);

        }

        @Test
        void  shouldNotFindByEmailAndPasswordIfUserDoesNotExist(){
            userDao.createUser(getUserEntity("usernam1@gmail.com", "username1"));
            Optional<UserEntity> actualResult = userDao.findUserByEmailAndPassword("fakeEmail", "fakePass");
            Assertions.assertThat(actualResult).isEmpty();
        }


    }

    private static UserEntity getUserEntity(String email, String username) {
        return UserEntity.builder()
                .username(username)
                .email(email)
                .password("123")
                .country(Country.Russia)
                .birthday(LocalDate.of(2000, 1, 1))
                .gender(Gender.MALE)
                .build();
    }
}