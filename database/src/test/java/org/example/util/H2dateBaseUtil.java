package org.example.util;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.Statement;

public abstract class H2dateBaseUtil {

    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS users(
                id BIGSERIAL PRIMARY KEY,
                username VARCHAR(132) NOT NULL UNIQUE,
                birthday DATE         NOT NULL,
                email    VARCHAR(132) NOT NULL UNIQUE,
                password VARCHAR(132) NOT NULL,
                gender   VARCHAR(64)  NOT NULL,
                country  VARCHAR(64)  NOT NULL)
            """;

    private static final String CLEAN_TABLE = """
            DELETE FROM users
            """;

    @BeforeAll
    @SneakyThrows
    void prepare() {
        @Cleanup Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(CREATE_TABLE);
    }

    @BeforeEach
    @SneakyThrows
    void cleanTable() {
        @Cleanup Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(CLEAN_TABLE);
    }
}
