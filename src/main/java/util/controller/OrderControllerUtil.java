package util.controller;

import facade.interaction.OrderFacade;
import facade.interaction.impl.OrderFacadeImpl;
import facade.relation.OrderActionsFacade;
import facade.relation.OrderCarPassportFacade;
import facade.relation.impl.OrderActionsFacadeImpl;
import facade.relation.impl.OrderCarPassportFacadeImpl;
import view.controller.AbstractServlet;
import view.dto.request.interaction.OrderDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.interaction.PassportDtoResponse;
import view.dto.response.product.CarDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderControllerUtil extends AbstractServlet {

    private final OrderFacade orderFacade = new OrderFacadeImpl();
    private final OrderCarPassportFacade ocpFacade = new OrderCarPassportFacadeImpl();
    private final OrderActionsFacade oasFacade = new OrderActionsFacadeImpl();

    public void deleteOrderPost(HttpServletRequest req) {
        orderFacade.delete(Long.parseLong(req.getParameter("id")));
    }

    public void hideOrderPost(HttpServletRequest req) {
        OrderDtoRequest orderDtoRequest = new OrderDtoRequest();
        OrderDtoResponse orderDtoResponse = orderFacade.findById(Long.parseLong(req.getParameter("id")));
        orderDtoRequest.setStart(orderDtoResponse.getStart());
        orderDtoRequest.setEnd(orderDtoResponse.getEnd());
        orderDtoRequest.setWithDriver(orderDtoResponse.getWithDriver());
        orderDtoRequest.setOrderStatus(orderDtoResponse.getOrderStatus());
        orderDtoRequest.setEnabled(!orderDtoResponse.getEnabled());
        orderFacade.updateAccess(orderDtoRequest, orderDtoResponse.getId());
    }

    public void infoOrderGet(HttpServletRequest req) {
        List<ActionDtoResponse> actions = oasFacade.findActionsByOrder(Long.parseLong(req.getParameter("id")));
        req.setAttribute("actions", actions);
        infoOrderGetSetup(req);
    }

    public void infoOrderManagerGet(HttpServletRequest req) {
        List<ActionDtoResponse> actions = oasFacade.findActionsByOrderFiltered(Long.parseLong(req.getParameter("id")));
        req.setAttribute("actions", actions);
        infoOrderGetSetup(req);
    }

    private void infoOrderGetSetup(HttpServletRequest req) {
        OrderDtoResponse orderResp = orderFacade.findById(Long.parseLong(req.getParameter("id")));
        CarDtoResponse carResp = ocpFacade.findCarByOrder(Long.parseLong(req.getParameter("id")));
        PassportDtoResponse passportResp = ocpFacade.findPassportByOrder(Long.parseLong(req.getParameter("id")));

        req.setAttribute("order", orderResp);
        req.setAttribute("car", carResp);
        req.setAttribute("passport", passportResp);
        req.setAttribute("fullCost", ocpFacade.calculateCost(orderResp.getId()));
    }

    public void ordersAdminGet(HttpServletRequest req) {
        AbstractServlet.HeaderName[] columnNames = getColumnNamesForOrdersAdmin();
        PageData<OrderDtoResponse> orders = orderFacade.findAll(req);
        List<AbstractServlet.HeaderData> headerData = getHeaderDataList(columnNames, orders);
        req.setAttribute("headerDataList", headerData);
        req.setAttribute("createUrl", "/admin/management/orders");
        req.setAttribute("cardHeader", "orders");
        req.setAttribute("pageData", orders);
        req.setAttribute("actionFacade", oasFacade);
    }

    public void ordersManagerGet(HttpServletRequest req) {
        AbstractServlet.HeaderName[] columnNames = getColumnNamesForOrders();
        PageData<OrderDtoResponse> orders = orderFacade.findAllFiltered(req);
        List<HeaderData> headerData = getHeaderDataList(columnNames, orders);
        req.setAttribute("headerDataList", headerData);
        req.setAttribute( "createUrl", "/moderator/management/orders");
        req.setAttribute("cardHeader", "orders");
        req.setAttribute("pageData", orders);
        req.setAttribute("actionFacade", oasFacade);
    }

    public AbstractServlet.HeaderName[] getColumnNamesForOrdersAdmin() {
        return new AbstractServlet.HeaderName[]{
                new AbstractServlet.HeaderName("Created", "created", "created"),
                new AbstractServlet.HeaderName("Updated", "updated", "updated"),
                new AbstractServlet.HeaderName("Start", "start", "start"),
                new AbstractServlet.HeaderName("End", "end", "end"),
                new AbstractServlet.HeaderName("With Driver", "withDriver", "with_driver"),
                new AbstractServlet.HeaderName("Status", "orderStatus", "order_status"),
                new AbstractServlet.HeaderName("Details", null, null),
                new AbstractServlet.HeaderName("Disable", null, null),
                new AbstractServlet.HeaderName("Delete", null, null)
        };
    }

    public AbstractServlet.HeaderName[] getColumnNamesForOrders() {
        return new AbstractServlet.HeaderName[]{
                new AbstractServlet.HeaderName("Created", "created", "created"),
                new AbstractServlet.HeaderName("Updated", "updated", "updated"),
                new AbstractServlet.HeaderName("Start", "start", "start"),
                new AbstractServlet.HeaderName("End", "end", "end"),
                new AbstractServlet.HeaderName("With Driver", "withDriver", "with_driver"),
                new AbstractServlet.HeaderName("Status", "orderStatus", "order_status"),
                new AbstractServlet.HeaderName("Details", null, null),
        };
    }
}