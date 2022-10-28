package util.controller;

import facade.interaction.ActionFacade;
import facade.interaction.OrderFacade;
import facade.interaction.impl.ActionFacadeImpl;
import facade.interaction.impl.OrderFacadeImpl;
import facade.relation.ManagerActionsFacade;
import facade.relation.OrderActionsFacade;
import facade.relation.UserOrdersFacade;
import facade.relation.impl.ManagerActionsFacadeImpl;
import facade.relation.impl.OrderActionsFacadeImpl;
import facade.relation.impl.UserOrdersFacadeImpl;
import persistence.entity.interaction.type.OrderStatus;
import view.controller.AbstractServlet;
import view.dto.request.interaction.ActionDtoRequest;
import view.dto.request.interaction.StatusDtoRequest;
import view.dto.request.relation.ManagerActionsDtoRequest;
import view.dto.request.relation.OrderActionsDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ActionControllerUtil extends AbstractServlet{

    private final ActionFacade actionFacade = new ActionFacadeImpl();
    private final OrderFacade orderFacade = new OrderFacadeImpl();
    private final OrderActionsFacade orderActionsFacade = new OrderActionsFacadeImpl();
    private final ManagerActionsFacade managerActionsFacade = new ManagerActionsFacadeImpl();
    private final UserOrdersFacade userOrdersFacade = new UserOrdersFacadeImpl();

    public void createActionGet(HttpServletRequest req) {
        OrderDtoResponse orderResponse = orderFacade.findById(Long.parseLong(req.getParameter("orderId")));
        List<OrderStatus> orderStatuses = Arrays.stream(OrderStatus.values()).collect(Collectors.toList());
        req.setAttribute("status", orderStatuses);
        req.setAttribute("order", orderResponse);
    }

    public OrderDtoResponse createActionPost(HttpServletRequest req){
        ActionDtoRequest actionRequest = new ActionDtoRequest();
        actionRequest.setIdentifier(req.getParameter("identifier"));
        actionRequest.setMessage(req.getParameter("message"));
        actionRequest.setEnabled(true);
        Long actionId = actionFacade.create(actionRequest);

        OrderDtoResponse orderResponse = orderFacade.findById(Long.parseLong(req.getParameter("orderId")));
        StatusDtoRequest statusRequest = new StatusDtoRequest();
        statusRequest.setOrderStatus(OrderStatus.valueOf(req.getParameter("orderStatus")));
        orderFacade.updateStatus(statusRequest, orderResponse.getId());

        UserDtoResponse userDtoResponse = (UserDtoResponse) req.getSession().getAttribute("user");

        OrderActionsDtoRequest orderActionsDtoRequest = new OrderActionsDtoRequest();
        orderActionsDtoRequest.setActionId(actionId);
        orderActionsDtoRequest.setOrderId(orderResponse.getId());
        orderActionsFacade.create(orderActionsDtoRequest);

        ManagerActionsDtoRequest managerActionsDtoRequest = new ManagerActionsDtoRequest();
        managerActionsDtoRequest.setUserId(userDtoResponse.getId());
        managerActionsDtoRequest.setActionId(actionId);
        managerActionsFacade.create(managerActionsDtoRequest);
        return orderResponse;
    }

    public void deleteActionPost(HttpServletRequest req) {
        actionFacade.delete(Long.parseLong(req.getParameter("id")));
    }

    public void editActionGet(HttpServletRequest req) {
        ActionDtoResponse action = actionFacade.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("action", action);
    }

    public ActionDtoResponse editActionPost(HttpServletRequest req) {
        ActionDtoRequest actionRequest = new ActionDtoRequest();
        ActionDtoResponse action = actionFacade.findById(Long.parseLong(req.getParameter("id")));
        actionRequest.setIdentifier(req.getParameter("identifier"));
        actionRequest.setMessage(req.getParameter("message"));
        actionRequest.setEnabled(action.getEnabled());
        actionFacade.update(actionRequest, action.getId());
        return action;
    }

    public void hideActionPost(HttpServletRequest req) {
        ActionDtoRequest actionDtoRequest = new ActionDtoRequest();
        ActionDtoResponse actionDtoResponse = actionFacade.findById(Long.parseLong(req.getParameter("id")));
        actionDtoRequest.setMessage(actionDtoResponse.getMessage());
        actionDtoRequest.setIdentifier(actionDtoResponse.getIdentifier());
        actionDtoRequest.setEnabled(!actionDtoResponse.getEnabled());
        actionFacade.updateAccess(actionDtoRequest, actionDtoResponse.getId());
    }

    public void infoActionGet(HttpServletRequest req) {
        ActionDtoResponse action = actionFacade.findById(Long.parseLong(req.getParameter("id")));
        UserDtoResponse manager = managerActionsFacade.findManagerByAction(action.getId());
        OrderDtoResponse order = orderActionsFacade.findOrderByAction(action.getId());
        UserDtoResponse customer = userOrdersFacade.findUserByOrder(order.getId());
        req.setAttribute("action", action);
        req.setAttribute("manager", manager);
        req.setAttribute("order", order);
        req.setAttribute("customer", customer);
    }

    public void actionsGetAdmin(HttpServletRequest req) {
        PageData<ActionDtoResponse> actions = actionFacade.findAll(req);
        AbstractServlet.HeaderName[] columnNames = getColumnNamesForActionsAdmin();
        List<AbstractServlet.HeaderData> headerData = getHeaderDataList(columnNames, actions);
        req.setAttribute("headerDataList", headerData);
        req.setAttribute( "createUrl", "/admin/management/actions");
        req.setAttribute("cardHeader", "actions");
        req.setAttribute("pageData", actions);
    }

    public void actionsGetManager(HttpServletRequest req) {
        PageData<ActionDtoResponse> actions = actionFacade.findAllFiltered(req);
        AbstractServlet.HeaderName[] columnNames = getColumnNamesForActions();
        List<HeaderData> headerData = getHeaderDataList(columnNames, actions);
        req.setAttribute("headerDataList", headerData);
        req.setAttribute( "createUrl", "/moderator/management/actions");
        req.setAttribute("cardHeader", "actions");
        req.setAttribute("pageData", actions);
    }

    private AbstractServlet.HeaderName[] getColumnNamesForActionsAdmin() {
        return new AbstractServlet.HeaderName[]{
                new AbstractServlet.HeaderName("Created", "created", "created"),
                new AbstractServlet.HeaderName("Updated", "updated", "updated"),
                new AbstractServlet.HeaderName("Identifier", "identifier", "identifier"),
                new AbstractServlet.HeaderName("Message", "message", "message"),
                new AbstractServlet.HeaderName("Details", null, null),
                new AbstractServlet.HeaderName("Disable", null, null),
                new AbstractServlet.HeaderName("Delete", null, null)
        };
    }

    private AbstractServlet.HeaderName[] getColumnNamesForActions() {
        return new AbstractServlet.HeaderName[]{
                new AbstractServlet.HeaderName("Created", "created", "created"),
                new AbstractServlet.HeaderName("Updated", "updated", "updated"),
                new AbstractServlet.HeaderName("Identifier", "identifier", "identifier"),
                new AbstractServlet.HeaderName("Message", "message", "message"),
                new AbstractServlet.HeaderName("Details", null, null),
        };
    }
}