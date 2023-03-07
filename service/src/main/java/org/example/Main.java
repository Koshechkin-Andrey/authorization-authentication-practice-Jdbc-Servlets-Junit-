package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatterExample dateTimeFormatterExample = new DateTimeFormatterExample();
        String test = "01-02-2000";
        boolean valid = dateTimeFormatterExample.isValid(test);
        System.out.println(valid);
    }
}