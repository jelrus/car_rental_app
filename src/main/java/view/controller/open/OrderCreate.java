package view.controller.open;

import exceptions.DateInputException;
import facade.interaction.OrderFacade;
import facade.interaction.PassportFacade;
import facade.interaction.impl.OrderFacadeImpl;
import facade.interaction.impl.PassportFacadeImpl;
import facade.product.CarFacade;
import facade.product.impl.CarFacadeImpl;
import facade.relation.OrderCarPassportFacade;
import facade.relation.UserOrdersFacade;
import facade.relation.impl.OrderCarPassportFacadeImpl;
import facade.relation.impl.UserOrdersFacadeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.entity.interaction.type.OrderStatus;
import persistence.entity.user.type.RoleType;
import view.dto.request.interaction.OrderDtoRequest;
import view.dto.request.interaction.PassportDtoRequest;
import view.dto.request.relation.OrderCarPassportDtoRequest;
import view.dto.request.relation.UserOrdersDtoRequest;
import view.dto.response.product.CarDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@WebServlet("/order/create/")
public class OrderCreate extends HttpServlet {

    private final OrderFacade orderFacade = new OrderFacadeImpl();
    private final PassportFacade passportFacade = new PassportFacadeImpl();
    private final CarFacade carFacade = new CarFacadeImpl();
    private final OrderCarPassportFacade orderCarPassportFacade = new OrderCarPassportFacadeImpl();
    private final UserOrdersFacade userOrdersFacade = new UserOrdersFacadeImpl();

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            req.getRequestDispatcher("/login").forward(req, resp);
        }

        CarDtoResponse carResponse = carFacade.findById(Long.parseLong(req.getParameter("carId")));
        req.setAttribute("car", carResponse);
        req.getRequestDispatcher("/open/order-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");

        if (user.getRoleType() != null) {
            CarDtoResponse carResponse = carFacade.findById(Long.parseLong(req.getParameter("carId")));

            OrderDtoRequest orderRequest = new OrderDtoRequest();
            setupOrder(req, orderRequest);
            Long orderId = orderFacade.create(orderRequest);

            PassportDtoRequest passportRequest = new PassportDtoRequest();
            setupPassport(req, passportRequest);
            Long passportId = passportFacade.create(passportRequest);

            OrderCarPassportDtoRequest ocpReq = new OrderCarPassportDtoRequest();
            ocpReq.setCarId(carResponse.getId());
            ocpReq.setOrderId(orderId);
            ocpReq.setPassportId(passportId);
            orderCarPassportFacade.create(ocpReq);

            UserOrdersDtoRequest userOrdersReq = new UserOrdersDtoRequest();
            userOrdersReq.setUserId(user.getId());
            userOrdersReq.setOrderId(orderId);
            userOrdersFacade.create(userOrdersReq);
        }

        resolveRedirect(user, resp);
    }

    private void setupOrder(HttpServletRequest req, OrderDtoRequest orderRequest) {
        try {
            orderRequest.setStart(parseDateFromString(req.getParameter("start")));
            orderRequest.setEnd(parseDateFromString(req.getParameter("end")));
        } catch (ParseException e) {
            LOGGER_ERROR.error("Start or end dates are incorrect");
        }

        orderRequest.setWithDriver(Objects.equals(req.getParameter("withDriver"), "on"));
        orderRequest.setOrderStatus(OrderStatus.PROCESSING);
        orderRequest.setEnabled(true);
    }

    private void setupPassport(HttpServletRequest req, PassportDtoRequest passportRequest) {
        passportRequest.setFirstName(req.getParameter("firstName"));
        passportRequest.setLastName(req.getParameter("lastName"));

        try {
            passportRequest.setBirthDate(parseDateFromString(req.getParameter("birthDate")));
        } catch (ParseException e) {
            LOGGER_ERROR.error("Birth date is incorrect");
        }

        passportRequest.setCountry(req.getParameter("country"));
        passportRequest.setZipCode(req.getParameter("zipCode"));
        passportRequest.setRegion(req.getParameter("region"));
        passportRequest.setCity(req.getParameter("city"));
        passportRequest.setStreet(req.getParameter("street"));
        passportRequest.setBuilding(req.getParameter("building"));
        passportRequest.setPhoneNumber(req.getParameter("phoneNumber"));
        passportRequest.setEmail(req.getParameter("email"));
    }

    private void resolveRedirect(UserDtoResponse user, HttpServletResponse resp) throws IOException {
        if (user.getRoleType() == null) {
            resp.sendRedirect("/login");
        }   else if (user.getRoleType().equals(RoleType.ROLE_ADMIN)) {
            resp.sendRedirect("/admin/personal/orders");
        } else if (user.getRoleType().equals(RoleType.ROLE_MANAGER)) {
            resp.sendRedirect("/moderator/personal/orders");
        } else if (user.getRoleType().equals(RoleType.ROLE_USER)){
            resp.sendRedirect("/user/personal/orders");
        }
    }

    private Date parseDateFromString(String date) throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(date);
    }
}