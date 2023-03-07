package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.UserDao;
import org.example.mappers.CreateMapperExample;
import org.example.mappers.ReadMapperExample;
import org.example.service.UserService;
import org.example.validation.RegistrationValidator;

import java.io.IOException;

import static org.example.util.GetJspPathUtil.getJspPath;
import static org.example.util.Uri.FIND_ALL;


@WebServlet(FIND_ALL)
public class FindAllUsersServlets extends HttpServlet {
    private final ReadMapperExample readMapper = new ReadMapperExample();
    private final CreateMapperExample createMapper = new CreateMapperExample();
    private final RegistrationValidator registrationValidator = new RegistrationValidator();
    private final UserDao userDao = new UserDao();

    private final UserService service = new UserService(readMapper, createMapper, registrationValidator, userDao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("users", service.findAll());
        req.getRequestDispatcher(getJspPath("FindAllPage")).forward(req, resp);

    }

}
