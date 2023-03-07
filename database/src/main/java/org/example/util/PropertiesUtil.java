package org.example.util;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }
    public static String getKey(String key) {
        return PROPERTIES.getProperty(key);
    }

    @SneakyThrows
    private static void loadProperties() {
        InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");
        PROPERTIES.load(resourceAsStream);
    }
}
