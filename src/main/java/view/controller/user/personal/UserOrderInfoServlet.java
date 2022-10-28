package view.controller.user.personal;

import facade.interaction.OrderFacade;
import facade.interaction.impl.OrderFacadeImpl;
import facade.relation.OrderActionsFacade;
import facade.relation.OrderCarPassportFacade;
import facade.relation.impl.OrderActionsFacadeImpl;
import facade.relation.impl.OrderCarPassportFacadeImpl;
import persistence.entity.user.type.RoleType;
import util.controller.PersonalControllerUtil;
import view.controller.AbstractServlet;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.interaction.PassportDtoResponse;
import view.dto.response.product.CarDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/user/personal/order/")
public class UserOrderInfoServlet extends AbstractServlet {

    private final PersonalControllerUtil personalControllerUtil = new PersonalControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_USER) {
            personalControllerUtil.orderInfoPersonalGet(req);
            getServletContext().getRequestDispatcher("/user/personal/order-info.jsp").forward(req, resp);
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