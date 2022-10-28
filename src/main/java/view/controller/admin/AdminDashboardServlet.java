package view.controller.admin;

import persistence.entity.user.type.RoleType;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}