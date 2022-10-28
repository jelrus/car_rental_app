package view.controller.manager.mangement.action;

import facade.interaction.ActionFacade;
import facade.interaction.OrderFacade;
import facade.interaction.impl.ActionFacadeImpl;
import facade.interaction.impl.OrderFacadeImpl;
import facade.relation.ManagerActionsFacade;
import facade.relation.OrderActionsFacade;
import facade.relation.impl.ManagerActionsFacadeImpl;
import facade.relation.impl.OrderActionsFacadeImpl;
import persistence.entity.interaction.type.OrderStatus;
import persistence.entity.user.type.RoleType;
import util.controller.ActionControllerUtil;
import view.dto.request.interaction.ActionDtoRequest;
import view.dto.request.interaction.StatusDtoRequest;
import view.dto.request.relation.ManagerActionsDtoRequest;
import view.dto.request.relation.OrderActionsDtoRequest;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/moderator/management/action/new/")
public class ActionCreateServlet extends HttpServlet {

    private final ActionControllerUtil actionControllerUtil = new ActionControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_MANAGER) {
            actionControllerUtil.createActionGet(req);
            getServletContext().getRequestDispatcher("/moderator/management/action-create.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_MANAGER) {
            resp.sendRedirect("/moderator/management/order/?id=" + actionControllerUtil.createActionPost(req).getId());
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}