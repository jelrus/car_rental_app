package view.controller.open;

import facade.product.CarFacade;
import facade.product.impl.CarFacadeImpl;
import persistence.entity.product.Car;
import persistence.entity.user.BaseUser;
import persistence.entity.user.type.RoleType;
import view.dto.response.PageData;
import view.dto.response.product.CarDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("")
public class MainServlet extends HttpServlet {

    private final CarFacade carFacade = new CarFacadeImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            BaseUser user = new BaseUser();
            user.setRoleType(null);
            UserDtoResponse userResponse = new UserDtoResponse(user);
            req.getSession().setAttribute("user", userResponse);
        }

        PageData<CarDtoResponse> cars = carFacade.findAllFiltered(req);
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
