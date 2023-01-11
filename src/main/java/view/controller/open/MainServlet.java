package view.controller.open;

import facade.product.CarFacade;
import facade.product.impl.CarFacadeImpl;
import persistence.dao.product.impl.CarDaoImpl;
import persistence.entity.product.Car;
import persistence.entity.user.BaseUser;
import persistence.entity.user.type.RoleType;
import service.product.impl.CarServiceImpl;
import view.controller.AbstractServlet;
import view.dto.response.PageData;
import view.dto.response.product.CarDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("")
public class MainServlet extends AbstractServlet {

    private final CarFacade carFacade = new CarFacadeImpl(new CarServiceImpl(new CarDaoImpl()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            BaseUser user = new BaseUser();
            user.setRoleType(null);
            UserDtoResponse userResponse = new UserDtoResponse(user);
            req.getSession().setAttribute("user", userResponse);
        }

        PageData<CarDtoResponse> cars = carFacade.findAllFiltered(req);

        AbstractServlet.HeaderName[] columnNames = getColumnNamesForCars();
        List<HeaderData> headerData = getHeaderDataList(columnNames, cars);
        req.setAttribute("headerDataList", headerData);
        req.setAttribute("pageData", cars);
        req.setAttribute( "createUrl", "/");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + createURL(req));
    }

    private AbstractServlet.HeaderName[] getColumnNamesForCars() {
        return new AbstractServlet.HeaderName[]{
                new AbstractServlet.HeaderName("Title", "title", "title"),
                new AbstractServlet.HeaderName("Price", "rentalPrice", "rental_price")
        };
    }
}
