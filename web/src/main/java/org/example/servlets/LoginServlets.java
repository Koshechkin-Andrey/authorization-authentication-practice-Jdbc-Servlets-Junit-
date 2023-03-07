package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.example.dao.UserDao;
import org.example.dto.UserDto;
import org.example.mappers.CreateMapperExample;
import org.example.mappers.ReadMapperExample;
import org.example.service.UserService;
import org.example.util.GetJspPathUtil;
import org.example.validation.RegistrationValidator;

import java.io.IOException;

import static org.example.util.Uri.FIND_ALL;
import static org.example.util.Uri.LOGIN;

@WebServlet(LOGIN)
public class LoginServlets extends HttpServlet {

    private final ReadMapperExample readMapper = new ReadMapperExample();
    private final CreateMapperExample createMapper = new CreateMapperExample();
    private final RegistrationValidator registrationValidator = new RegistrationValidator();
    private final UserDao userDao = new UserDao();

    private final UserService service = new UserService(readMapper, createMapper, registrationValidator, userDao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(GetJspPathUtil.getJspPath("LoginPage")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.findUserByEmailAndPassword(req.getParameter("email"), req.getParameter("password"))
                .ifPresentOrElse(user -> LoginSuccess(user, req, resp), () ->
                    LoginFail(req, resp));
    }

    @SneakyThrows
    private void LoginSuccess(UserDto user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect(FIND_ALL);
    }

    @SneakyThrows
    private void LoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect(LOGIN + "?error");
    }
}
