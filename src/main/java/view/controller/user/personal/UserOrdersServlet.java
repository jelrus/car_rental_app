package view.controller.user.personal;

import facade.relation.OrderActionsFacade;
import facade.relation.UserOrdersFacade;
import facade.relation.impl.OrderActionsFacadeImpl;
import facade.relation.impl.UserOrdersFacadeImpl;
import persistence.entity.user.type.RoleType;
import util.controller.PersonalControllerUtil;
import view.controller.AbstractServlet;
import view.dto.response.PageData;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/user/personal/orders")
public class UserOrdersServlet extends AbstractServlet {

    private final PersonalControllerUtil personalControllerUtil = new PersonalControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_USER) {
            personalControllerUtil.ordersPersonalUserGet(req);
            getServletContext().getRequestDispatcher("/user/personal/orders.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_USER) {
            resp.sendRedirect(req.getContextPath() + createURL(req));
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}