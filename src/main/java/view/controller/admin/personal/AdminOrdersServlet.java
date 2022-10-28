package view.controller.admin.personal;

import persistence.entity.user.type.RoleType;
import util.controller.PersonalControllerUtil;
import view.controller.AbstractServlet;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/admin/personal/orders")
public class AdminOrdersServlet extends AbstractServlet {

    private final PersonalControllerUtil personalControllerUtil = new PersonalControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            personalControllerUtil.ordersPersonalGet(req);
            getServletContext().getRequestDispatcher("/admin/personal/orders.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            resp.sendRedirect(req.getContextPath() + createURL(req));
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}