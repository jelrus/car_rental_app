package view.controller.security;

import facade.security.RegistrationFacade;
import facade.security.impl.RegistrationFacadeImpl;
import facade.user.BaseUserFacade;
import facade.user.impl.UserFacadeImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final RegistrationFacade registrationFacade = new RegistrationFacadeImpl();
    private final BaseUserFacade baseUserFacade = new UserFacadeImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
    }
}
