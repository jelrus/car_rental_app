package view.controller.manager.mangement.action;

import facade.interaction.ActionFacade;
import facade.interaction.impl.ActionFacadeImpl;
import persistence.entity.user.type.RoleType;
import util.controller.ActionControllerUtil;
import view.dto.request.interaction.ActionDtoRequest;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/moderator/management/edit/action/")
public class ActionEditServlet extends HttpServlet {

    private final ActionControllerUtil actionControllerUtil = new ActionControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_MANAGER) {
            actionControllerUtil.editActionGet(req);
            req.getRequestDispatcher("/moderator/management/action-edit.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_MANAGER) {
            resp.sendRedirect("/moderator/management/edit/action/?id=" + actionControllerUtil.editActionPost(req).getId());
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}