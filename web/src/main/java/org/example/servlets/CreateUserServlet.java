package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.UserDao;
import org.example.dto.UserDto;
import org.example.mappers.CreateMapperExample;
import org.example.mappers.ReadMapperExample;
import org.example.service.UserService;
import org.example.util.GetJspPathUtil;
import org.example.validation.RegistrationValidator;
import org.example.validation.ValidatorException;

import java.io.IOException;
import java.util.List;

import static org.example.util.Uri.LOGIN;
import static org.example.util.Uri.REGISTRATION_USER;

@WebServlet(REGISTRATION_USER)
public class CreateUserServlet extends HttpServlet {

    private final ReadMapperExample readMapper = new ReadMapperExample();
    private final CreateMapperExample createMapper = new CreateMapperExample();
    private final RegistrationValidator registrationValidator = new RegistrationValidator();
    private final UserDao userDao = new UserDao();

    private final UserService service = new UserService(readMapper, createMapper, registrationValidator, userDao);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("gender", List.of("MALE", "FEMALE"));
        req.setAttribute("country", List.of("Russia", "Germany", "Japan"));
        req.getRequestDispatcher(GetJspPathUtil.getJspPath("RegistrationPage")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = UserDto.builder()
                .username(req.getParameter("username"))
                .birthday(req.getParameter("birthday"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .gender(req.getParameter("gender"))
                .country(req.getParameter("country"))
                .build();

        try {
            service.createUser(userDto);
            resp.sendRedirect(LOGIN);
        } catch (ValidatorException exception) {
            req.setAttribute("error", exception.getErrorList());
            doGet(req, resp);
        }

    }
}
