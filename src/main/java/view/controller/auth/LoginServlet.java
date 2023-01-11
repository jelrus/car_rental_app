package view.controller.auth;

import facade.user.UserFacade;
import facade.user.impl.UserFacadeImpl;
import persistence.dao.user.impl.UserDaoImpl;
import persistence.entity.user.type.RoleType;
import service.user.impl.UserServiceImpl;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserFacade userFacade = new UserFacadeImpl(new UserServiceImpl(new UserDaoImpl()));
        UserDtoResponse user = userFacade.findByUsernamePassword(username, password);
            if (user != null && user.getEnabled()) {
                req.getSession().setAttribute("user", user);

                if (user.getRoleType().equals(RoleType.ROLE_ADMIN)) {
                    resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
                    return;
                } else if (user.getRoleType().equals(RoleType.ROLE_MANAGER)) {
                    resp.sendRedirect(req.getContextPath() + "/moderator/dashboard");
                    return;
                } else if (user.getRoleType().equals(RoleType.ROLE_USER)) {
                    resp.sendRedirect(req.getContextPath() + "/user/dashboard");
                    return;
                }
            }

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}