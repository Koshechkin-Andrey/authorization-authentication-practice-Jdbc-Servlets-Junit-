package org.example.util;

public class GetJspPathUtil {
    private static final String path = "/WEB-INF/jsp/%s.jsp";

    public static String getJspPath(String name) {
        return String.format(path, name);
    }
}
