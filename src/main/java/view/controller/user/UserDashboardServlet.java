package view.controller.user;

import persistence.entity.user.type.RoleType;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/user/dashboard")
public class UserDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_USER) {
            req.getRequestDispatcher("/user/dashboard.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}