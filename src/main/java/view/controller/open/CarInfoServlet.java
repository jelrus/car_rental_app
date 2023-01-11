package view.controller.open;

import facade.product.CarFacade;
import facade.product.impl.CarFacadeImpl;
import persistence.dao.product.impl.CarDaoImpl;
import service.product.impl.CarServiceImpl;
import view.dto.response.product.CarDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product/")
public class CarInfoServlet extends HttpServlet {

    private final CarFacade carFacade = new CarFacadeImpl(new CarServiceImpl(new CarDaoImpl()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarDtoResponse carResponse = carFacade.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("car", carResponse);
        req.getRequestDispatcher("/open/product.jsp").forward(req, resp);
    }
}