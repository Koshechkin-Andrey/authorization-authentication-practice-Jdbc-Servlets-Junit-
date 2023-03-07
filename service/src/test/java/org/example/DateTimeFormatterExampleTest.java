package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DateTimeFormatterExampleTest {


    @Test
    void format() {
        String date = "2023-03-07";

        LocalDate actualResult = DateTimeFormatterExample.format(date);
        LocalDate expectedResult = LocalDate.of(2023, 3, 7);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void formatterFail() {
        String date1 = "2023-03-07-14:22";
        String date2 = "";

        assertThrows(DateTimeParseException.class, () -> DateTimeFormatterExample.format(date1));
        assertThrows(DateTimeParseException.class, () -> DateTimeFormatterExample.format(date2));
    }


    @ParameterizedTest
    @MethodSource("getArgument")
    void isValidTest(String date, boolean bool) {
        boolean result = DateTimeFormatterExample.isValid(date);
        assertThat(result).isEqualTo(bool);
    }

    static Stream<Arguments> getArgument() {
        return Stream.of(
                Arguments.of("2000-01-01", true),
                Arguments.of("01-01-2000", false),
                Arguments.of("03-07-14:13", false),
                Arguments.of("2019-10-12", true)
        );
    }
}