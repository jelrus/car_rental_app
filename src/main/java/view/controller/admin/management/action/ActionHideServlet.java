package view.controller.admin.management.action;

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

@WebServlet("/admin/management/actions/hide/")
public class ActionHideServlet extends HttpServlet {

    private final ActionControllerUtil actionControllerUtil = new ActionControllerUtil();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            actionControllerUtil.hideActionPost(req);
            resp.sendRedirect("/admin/management/actions");
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}