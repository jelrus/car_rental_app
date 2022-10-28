package view.controller.manager.mangement.action;

import facade.interaction.ActionFacade;
import facade.interaction.impl.ActionFacadeImpl;
import facade.relation.ManagerActionsFacade;
import facade.relation.OrderActionsFacade;
import facade.relation.UserOrdersFacade;
import facade.relation.impl.ManagerActionsFacadeImpl;
import facade.relation.impl.OrderActionsFacadeImpl;
import facade.relation.impl.UserOrdersFacadeImpl;
import persistence.entity.user.type.RoleType;
import util.controller.ActionControllerUtil;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/moderator/management/action/")
public class ActionInfoServlet extends HttpServlet {

    private final ActionControllerUtil actionControllerUtil = new ActionControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_MANAGER) {
            actionControllerUtil.infoActionGet(req);
            req.getRequestDispatcher("/moderator/management/action-info.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}