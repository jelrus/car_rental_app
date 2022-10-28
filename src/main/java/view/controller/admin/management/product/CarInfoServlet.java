package view.controller.admin.management.product;

import persistence.entity.user.type.RoleType;
import util.controller.CarControllerUtil;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/admin/product/car/")
public class CarInfoServlet extends HttpServlet {

    private final CarControllerUtil carControllerUtil = new CarControllerUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (Objects.requireNonNull(user).getRoleType() == RoleType.ROLE_ADMIN) {
            carControllerUtil.infoCarGet(req);
            req.getRequestDispatcher("/admin/management/car-info.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/nowhere");
        }
    }
}
