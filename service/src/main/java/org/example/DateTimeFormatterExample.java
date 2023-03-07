package org.example;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateTimeFormatterExample {

    private static final String ofPattern = "yyyy-MM-dd";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ofPattern);

    public static LocalDate format(String date) {
        return LocalDate.parse(date, formatter);
    }


    public static boolean isValid(String date) {
        try {
            return Optional.ofNullable(date)
                    .map(DateTimeFormatterExample::format)
                    .isPresent();
        } catch (DateTimeParseException e) {
            return false;
        }


    }
}
