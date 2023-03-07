package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.example.util.Uri.LOCALE;
import static org.example.util.Uri.LOGIN;

@WebServlet(LOCALE)
public class LocaleServlets extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locale = req.getParameter("locale");
        req.getSession().setAttribute("locale", locale);

        String referer = req.getHeader("referer");
        String prevPage = referer != null ? referer : LOGIN;
        resp.sendRedirect(prevPage + "?locale=" + locale);

        System.out.println();
    }
}
