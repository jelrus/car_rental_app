package view.controller.auth;

import facade.user.UserFacade;
import facade.user.impl.UserFacadeImpl;
import view.dto.request.user.AuthDtoRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    UserFacade userFacade = new UserFacadeImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthDtoRequest authDto = new AuthDtoRequest();
        authDto.setUsername(req.getParameter("username"));
        authDto.setPassword(req.getParameter("password"));
        userFacade.register(authDto);
        resp.sendRedirect("/login");
    }
}