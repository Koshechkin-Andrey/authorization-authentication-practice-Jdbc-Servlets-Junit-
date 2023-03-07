package org.example.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@Tag("TestH2bdConnection")
class PropertiesUtilTest {

    @ParameterizedTest
    @MethodSource("getArgumentForSuccessGetProperties")
    void shouldSuccessIfPropertiesExist(String key, String value) {
        String actualResult = PropertiesUtil.getKey(key);
        Assertions.assertThat(actualResult).isEqualTo(value);
    }

    static Stream<Arguments> getArgumentForSuccessGetProperties() {
        return Stream.of(
                Arguments.of("db.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"),
                Arguments.of("db.username", "sa"),
                Arguments.of("db.password", "")
        );
    }


    @ParameterizedTest
    @MethodSource("getArgumentForFailGetProperties")
    void shouldSuccessIfPropertiesIsNoExist(String key, String value) {
        String actualResult = PropertiesUtil.getKey(key);
        Assertions.assertThat(actualResult).isNotEqualTo(value);
    }

    static Stream<Arguments> getArgumentForFailGetProperties() {
        return Stream.of(
                Arguments.of("db.url", "dummy"),
                Arguments.of("db.username", "dummy"),
                Arguments.of("db.password", "dummy")
        );
    }
}