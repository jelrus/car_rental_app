package util.controller;

import facade.interaction.OrderFacade;
import facade.interaction.impl.OrderFacadeImpl;
import facade.relation.OrderActionsFacade;
import facade.relation.OrderCarPassportFacade;
import facade.relation.UserOrdersFacade;
import facade.relation.impl.OrderActionsFacadeImpl;
import facade.relation.impl.OrderCarPassportFacadeImpl;
import facade.relation.impl.UserOrdersFacadeImpl;
import facade.user.UserFacade;
import facade.user.impl.UserFacadeImpl;
import view.controller.AbstractServlet;
import view.dto.request.user.AccessDtoRequest;
import view.dto.request.user.AuthDtoRequest;
import view.dto.request.user.InfoDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.interaction.PassportDtoResponse;
import view.dto.response.product.CarDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PersonalControllerUtil extends AbstractServlet {

    private final UserFacade userFacade = new UserFacadeImpl();
    private final OrderFacade orderFacade = new OrderFacadeImpl();
    private final OrderCarPassportFacade ocpFacade = new OrderCarPassportFacadeImpl();
    private final OrderActionsFacade oasFacade = new OrderActionsFacadeImpl();
    private final UserOrdersFacade userOrdersFacade = new UserOrdersFacadeImpl();
    private final OrderControllerUtil orderControllerUtil = new OrderControllerUtil();

    public void editPersonalProfilePost(HttpServletRequest req) {
        UserDtoResponse userDtoResponse = (UserDtoResponse) req.getSession().getAttribute("user");
        AuthDtoRequest authDto = setupAuthDto(req);
        InfoDtoRequest infoDto = setupInfoDto(req);

        userFacade.updateSecurityInfo(authDto, userDtoResponse.getId());
        userFacade.updateInfo(infoDto, userDtoResponse.getId());
    }

    public void orderInfoPersonalGet(HttpServletRequest req) {
        OrderDtoResponse orderResp = orderFacade.findById(Long.parseLong(req.getParameter("id")));
        CarDtoResponse carResp = ocpFacade.findCarByOrder(Long.parseLong(req.getParameter("id")));
        PassportDtoResponse passportResp = ocpFacade.findPassportByOrder(Long.parseLong(req.getParameter("id")));
        List<ActionDtoResponse> actions = oasFacade.findActionsByOrderFiltered(Long.parseLong(req.getParameter("id")));

        req.setAttribute("order", orderResp);
        req.setAttribute("car", carResp);
        req.setAttribute("passport", passportResp);
        req.setAttribute("actions", actions);
        req.setAttribute("fullCost", ocpFacade.calculateCost(orderResp.getId()));
    }

    public void ordersPersonalGet(HttpServletRequest req) {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");
        PageData<OrderDtoResponse> orders = userOrdersFacade.findOrdersByUser(user.getId(), req);
        AbstractServlet.HeaderName[] columnNames = orderControllerUtil.getColumnNamesForOrdersAdmin();
        List<HeaderData> headerData = getHeaderDataList(columnNames, orders);
        req.setAttribute("headerDataList", headerData);
        req.setAttribute( "createUrl", "/admin/personal/orders");
        req.setAttribute("cardHeader", "orders");
        req.setAttribute("pageData", orders);
        req.setAttribute("actionFacade", oasFacade);
    }

    public void ordersPersonalManagerGet(HttpServletRequest req) {
        setupOrdersForUser(req);
        req.setAttribute( "createUrl", "/moderator/personal/orders");
    }

    public void ordersPersonalUserGet(HttpServletRequest req) {
        setupOrdersForUser(req);
        req.setAttribute( "createUrl", "/user/personal/orders");
    }

    public void suspendUserPost(HttpServletRequest req) {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");
        AccessDtoRequest accessDtoRequest = new AccessDtoRequest();
        accessDtoRequest.setEnabled(false);
        userFacade.updateAccess(accessDtoRequest, user.getId());
        req.getSession().removeAttribute("user");
    }

    private void setupOrdersForUser(HttpServletRequest req) {
        UserDtoResponse user = (UserDtoResponse) req.getSession().getAttribute("user");
        PageData<OrderDtoResponse> orders = userOrdersFacade.findOrdersByUser(user.getId(), req);
        AbstractServlet.HeaderName[] columnNames = orderControllerUtil.getColumnNamesForOrders();
        List<HeaderData> headerData = getHeaderDataList(columnNames, orders);
        req.setAttribute("headerDataList", headerData);
        req.setAttribute("cardHeader", "orders");
        req.setAttribute("pageData", orders);
        req.setAttribute("actionFacade", oasFacade);
    }

    private AuthDtoRequest setupAuthDto(HttpServletRequest req) {
        AuthDtoRequest authDto = new AuthDtoRequest();
        authDto.setUsername(req.getParameter("username"));
        authDto.setPassword(req.getParameter("password"));
        return authDto;
    }

    private InfoDtoRequest setupInfoDto(HttpServletRequest req) {
        InfoDtoRequest infoDto = new InfoDtoRequest();
        infoDto.setFirstName(req.getParameter("firstName"));
        infoDto.setLastName(req.getParameter("lastName"));
        infoDto.setProfilePic(req.getParameter("profilePic"));
        infoDto.setDescription(req.getParameter("description"));
        return infoDto;
    }
}