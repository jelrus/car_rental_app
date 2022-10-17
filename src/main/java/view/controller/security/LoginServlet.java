package view.controller.security;

import persistence.entity.user.BaseUser;
import persistence.entity.user.impl.User;
import persistence.entity.user.type.UserRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseUser user = new BaseUser();
        user.setId(1L);
        user.setRoleType(UserRole.ADMIN);
        user.setUsername("jelrus");
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
