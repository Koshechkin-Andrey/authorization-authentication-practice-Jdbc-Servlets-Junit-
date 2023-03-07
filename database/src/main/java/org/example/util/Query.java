package org.example.util;

public class Query {

    public static final String FIND_ALL = """
            SELECT 
            u.id,
            u.username,
            u.birthday,
            u.email,
            u.password,
            u.gender,
            u.country
            FROM users u
             """;

    public static final String CREATE_USER = """
            INSERT INTO users (username, birthday, email, password, gender, country)
            VALUES (?,?,?,?,?,?)
            """;

    public static final String FIND_USER_BY_EMAIL_AND_PASSWORD = """
            SELECT 
            u.id,
            u.username,
            u.birthday,
            u.email,
            u.password,
            u.gender,
            u.country
            FROM users u WHERE email = ? AND password = ?
            """;
}
