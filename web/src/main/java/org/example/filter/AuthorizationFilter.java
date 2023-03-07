package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.UserDto;

import java.io.IOException;
import java.util.Set;

import static org.example.util.Uri.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private final Set<String> PUBLIC_URI = Set.of(LOGIN, REGISTRATION_USER, LOGOUT, LOCALE);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublic(uri) || loginSuccess(servletRequest, servletResponse)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(LOGIN);
        }
    }

    private boolean isPublic(String uri) {
        return PUBLIC_URI.stream().anyMatch(uri::startsWith);
    }

    private boolean loginSuccess(ServletRequest servletRequest, ServletResponse servletResponse) {
        UserDto user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }


}
