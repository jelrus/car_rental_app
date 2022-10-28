package view.controller.admin.management.action;

import persistence.entity.user.type.RoleType;
import util.controller.ActionControllerUtil;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/admin/management/action/new/")
public class ActionCreateServlet extends HttpServlet {

    private final ActionControllerUtil actionControllerUtil = new ActionControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            actionControllerUtil.createActionGet(req);
            getServletContext().getRequestDispatcher("/admin/management/action-create.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/admin/management/order/?id=" + actionControllerUtil.createActionPost(req).getId());
    }
}