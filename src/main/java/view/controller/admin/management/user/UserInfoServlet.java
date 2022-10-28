package view.controller.admin.management.user;

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

@WebServlet("/admin/management/user/")
public class UserInfoServlet extends HttpServlet {

    private final UserControllerUtil userControllerUtil = new UserControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            userControllerUtil.infoUserGet(req);
            req.getRequestDispatcher("/admin/management/user-info.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}