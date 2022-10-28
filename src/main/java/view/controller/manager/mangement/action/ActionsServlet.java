package view.controller.manager.mangement.action;

import facade.interaction.ActionFacade;
import facade.interaction.impl.ActionFacadeImpl;
import persistence.entity.user.type.RoleType;
import util.controller.ActionControllerUtil;
import view.controller.AbstractServlet;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/moderator/management/actions")
public class ActionsServlet extends AbstractServlet {

    private final ActionControllerUtil actionControllerUtil = new ActionControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_MANAGER) {
            actionControllerUtil.actionsGetManager(req);
            getServletContext().getRequestDispatcher("/moderator/management/actions.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_MANAGER) {
            resp.sendRedirect(req.getContextPath() + createURL(req));
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}