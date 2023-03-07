package org.example.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Country {
    Russia,
    Germany,
    Japan;

    public static Optional<Country> findOpt(String country) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(country))
                .findFirst();
    }
}
