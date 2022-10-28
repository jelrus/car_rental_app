package view.controller.admin.management.order;

import persistence.entity.user.type.RoleType;
import util.controller.OrderControllerUtil;
import view.controller.AbstractServlet;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/admin/management/order/")
public class OrderInfoServlet extends AbstractServlet {

    private final OrderControllerUtil orderControllerUtil = new OrderControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            orderControllerUtil.infoOrderGet(req);
            getServletContext().getRequestDispatcher("/admin/management/order-info.jsp").forward(req, resp);
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
