package org.example.util;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
  /*  private static final String DRIVER_KEY = "db.driver";*/

    static {
        loadDriver();

    }


    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(
                PropertiesUtil.getKey(URL_KEY),
                PropertiesUtil.getKey(USERNAME_KEY),
                PropertiesUtil.getKey(PASSWORD_KEY)

        );
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("org.postgresql.Driver");
    }
}
