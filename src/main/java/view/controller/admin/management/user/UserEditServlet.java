package view.controller.admin.management.user;

import facade.user.UserFacade;
import facade.user.impl.UserFacadeImpl;
import persistence.entity.user.type.RoleType;
import util.controller.UserControllerUtil;
import view.dto.request.user.*;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/admin/management/edit/user/")
public class UserEditServlet extends HttpServlet {

    private final UserControllerUtil userControllerUtil = new UserControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            userControllerUtil.editUserGet(req);
            req.getRequestDispatcher("/admin/management/user-edit.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            resp.sendRedirect("/admin/management/user/?id=" + userControllerUtil.editUserPost(req).getId());
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}