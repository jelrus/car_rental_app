package view.controller.admin.management.manager;

import persistence.entity.user.type.RoleType;
import util.controller.UserControllerUtil;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/admin/management/manager/new")
public class ManagerCreateServlet extends HttpServlet {

    private final UserControllerUtil userControllerUtil = new UserControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            req.getRequestDispatcher("/admin/management/manager-create.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            userControllerUtil.createManagerPost(req);
            resp.sendRedirect("/admin/management/managers");
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}