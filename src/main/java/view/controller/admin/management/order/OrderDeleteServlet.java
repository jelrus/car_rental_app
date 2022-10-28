package view.controller.admin.management.order;

import persistence.entity.user.type.RoleType;
import util.controller.OrderControllerUtil;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/admin/management/orders/delete/")
public class OrderDeleteServlet extends HttpServlet {

    private final OrderControllerUtil orderControllerUtil = new OrderControllerUtil();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            orderControllerUtil.deleteOrderPost(req);
            resp.sendRedirect("/admin/management/orders");
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}
