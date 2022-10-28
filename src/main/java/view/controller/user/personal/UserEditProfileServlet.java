package view.controller.user.personal;

import facade.user.UserFacade;
import facade.user.impl.UserFacadeImpl;
import persistence.entity.user.type.RoleType;
import util.controller.PersonalControllerUtil;
import view.dto.request.user.AuthDtoRequest;
import view.dto.request.user.InfoDtoRequest;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/user/personal/profile/edit/")
public class UserEditProfileServlet extends HttpServlet {

    private final PersonalControllerUtil personalControllerUtil = new PersonalControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_USER) {
            req.getRequestDispatcher("/user/personal/edit-profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_USER) {
            personalControllerUtil.editPersonalProfilePost(req);
            resp.sendRedirect("/logout");
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}