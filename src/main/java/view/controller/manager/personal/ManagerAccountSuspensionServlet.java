package view.controller.manager.personal;

import persistence.entity.user.type.RoleType;
import util.controller.PersonalControllerUtil;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/moderator/profile/suspension")
public class ManagerAccountSuspensionServlet extends HttpServlet {

    private final PersonalControllerUtil personalControllerUtil = new PersonalControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_MANAGER) {
            req.getRequestDispatcher("/moderator/personal/account-suspension.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_MANAGER) {
            personalControllerUtil.suspendUserPost(req);
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}