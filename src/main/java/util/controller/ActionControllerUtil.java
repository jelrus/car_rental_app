package util.controller;

import facade.interaction.ActionFacade;
import facade.interaction.OrderFacade;
import facade.interaction.impl.ActionFacadeImpl;
import facade.interaction.impl.OrderFacadeImpl;
import facade.relation.*;
import facade.relation.impl.*;
import facade.user.UserFacade;
import facade.user.impl.UserFacadeImpl;
import persistence.entity.interaction.type.OrderStatus;
import util.invoice.InvoiceGenerator;
import util.invoice.objects.Member;
import util.invoice.objects.impl.DamageRefundInvoice;
import util.invoice.objects.impl.StandardInvoice;
import view.controller.AbstractServlet;
import view.dto.request.interaction.ActionDtoRequest;
import view.dto.request.interaction.StatusDtoRequest;
import view.dto.request.relation.InvoicesOrderDtoRequest;
import view.dto.request.relation.ManagerActionsDtoRequest;
import view.dto.request.relation.OrderActionsDtoRequest;
import view.dto.request.user.BalanceDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.interaction.PassportDtoResponse;
import view.dto.response.product.CarDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ActionControllerUtil extends AbstractServlet{

    private final ActionFacade actionFacade = new ActionFacadeImpl();
    private final OrderFacade orderFacade = new OrderFacadeImpl();
    private final OrderActionsFacade orderActionsFacade = new OrderActionsFacadeImpl();
    private final InvoicesOrderFacade invoicesOrderFacade = new InvoicesOrderFacadeImpl();
    private final ManagerActionsFacade managerActionsFacade = new ManagerActionsFacadeImpl();
    private final UserOrdersFacade userOrdersFacade = new UserOrdersFacadeImpl();
    private final OrderCarPassportFacade orderCarPassportFacade = new OrderCarPassportFacadeImpl();
    private final UserFacade userFacade = new UserFacadeImpl();

    public void createActionGet(HttpServletRequest req) {
        OrderDtoResponse orderResponse = orderFacade.findById(Long.parseLong(req.getParameter("orderId")));
        List<OrderStatus> orderStatuses = Arrays.stream(OrderStatus.values()).collect(Collectors.toList());
        req.setAttribute("status", orderStatuses);
        req.setAttribute("order", orderResponse);
    }

    public OrderDtoResponse createActionPost(HttpServletRequest req){
        ActionDtoRequest actionRequest = setupActionRequest(req);
        Long actionId = actionFacade.create(actionRequest);

        OrderDtoResponse order = orderFacade.findById(Long.parseLong(req.getParameter("orderId")));
        StatusDtoRequest statusRequest = new StatusDtoRequest();
        statusRequest.setOrderStatus(OrderStatus.valueOf(req.getParameter("orderStatus")));
        orderFacade.updateStatus(statusRequest, order.getId());

        if (Objects.equals(statusRequest.getOrderStatus().getOrderStatus(), "Approved")) {
            approveOrder(order);
        }

        if (Objects.equals(statusRequest.getOrderStatus().getOrderStatus(), "Damage Refund")) {
            damageRefund(order, req);
        }

        UserDtoResponse userDtoResponse = (UserDtoResponse) req.getSession().getAttribute("user");

        OrderActionsDtoRequest orderActionsDtoRequest = new OrderActionsDtoRequest();
        orderActionsDtoRequest.setActionId(actionId);
        orderActionsDtoRequest.setOrderId(order.getId());
        orderActionsFacade.create(orderActionsDtoRequest);

        ManagerActionsDtoRequest managerActionsDtoRequest = new ManagerActionsDtoRequest();
        managerActionsDtoRequest.setUserId(userDtoResponse.getId());
        managerActionsDtoRequest.setActionId(actionId);
        managerActionsFacade.create(managerActionsDtoRequest);
        return order;
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

    private ActionDtoRequest setupActionRequest(HttpServletRequest req) {
        ActionDtoRequest actionRequest = new ActionDtoRequest();
        actionRequest.setIdentifier(req.getParameter("identifier"));
        actionRequest.setMessage(req.getParameter("message"));
        actionRequest.setEnabled(true);
        return actionRequest;
    }

    private Member setupRecipient(PassportDtoResponse passport) {
        Member recipient = new Member();
        recipient.setName(passport.getFirstName() + " " + passport.getLastName());
        recipient.setCountry(passport.getCountry());
        recipient.setZipCode(passport.getZipCode());
        recipient.setCity(passport.getCity());
        recipient.setStreet(passport.getStreet());
        recipient.setBuilding("b." + passport.getBuilding());
        recipient.setPhone(passport.getPhoneNumber());
        recipient.setEmail(passport.getEmail());
        return recipient;
    }

    private StandardInvoice setupStandardInvoice(OrderDtoResponse order, CarDtoResponse car, Member s, Member r) {
        StandardInvoice si = new StandardInvoice(s, r);
        si.setFullTitle(car.getTitle() + " (" + car.getCarBrand().getCarBrand() + " " +
                car.getCarQuality().getCarQuality() + ") ");
        si.setRentalPrice(car.getRentalPrice().toPlainString());
        si.setDaysOfUsage(calculateDays(order));
        si.setTotalPrice(orderCarPassportFacade.calculateCost(order.getId()).toPlainString());
        return si;
    }

    private String calculateDays(OrderDtoResponse order) {
        return String.valueOf(TimeUnit.DAYS.convert(order.getEnd().getTime() - order.getStart().getTime(),
                              TimeUnit.MILLISECONDS));
    }

    private InvoicesOrderDtoRequest setupInvoiceDtoRequest(StandardInvoice si, OrderDtoResponse order) {
        InvoicesOrderDtoRequest invoiceDtoReq = new InvoicesOrderDtoRequest();
        invoiceDtoReq.setEnabled(true);
        invoiceDtoReq.setOrderId(order.getId());
        invoiceDtoReq.setFileLink(InvoiceGenerator.generateStandardInvoice(si));
        return invoiceDtoReq;
    }

    private InvoicesOrderDtoRequest setupInvoiceDtoRequest(DamageRefundInvoice dri, OrderDtoResponse order) {
        InvoicesOrderDtoRequest invoiceDtoReq = new InvoicesOrderDtoRequest();
        invoiceDtoReq.setEnabled(true);
        invoiceDtoReq.setOrderId(order.getId());
        invoiceDtoReq.setFileLink(InvoiceGenerator.generateDamageRefundInvoice(dri));
        return invoiceDtoReq;
    }

    private void approveOrder(OrderDtoResponse order) {
        CarDtoResponse car = orderCarPassportFacade.findCarByOrder(order.getId());
        PassportDtoResponse passport = orderCarPassportFacade.findPassportByOrder(order.getId());
        Member sender = InvoiceGenerator.generateCompany();
        Member recipient = setupRecipient(passport);
        StandardInvoice standardInvoice = setupStandardInvoice(order, car, sender, recipient);
        InvoicesOrderDtoRequest invoiceDtoReq = setupInvoiceDtoRequest(standardInvoice, order);
        invoicesOrderFacade.create(invoiceDtoReq);
        UserDtoResponse user = userOrdersFacade.findUserByOrder(order.getId());
        BalanceDtoRequest balance = new BalanceDtoRequest();
        balance.setBalance(user.getBalance().subtract(new BigDecimal(standardInvoice.getTotalPrice())));
        userFacade.updateBalance(balance, user.getId());
    }

    private void damageRefund(OrderDtoResponse order, HttpServletRequest req) {
        PassportDtoResponse passport = orderCarPassportFacade.findPassportByOrder(order.getId());
        Member sender = InvoiceGenerator.generateCompany();
        Member recipient = setupRecipient(passport);
        DamageRefundInvoice damageRefundInvoice = new DamageRefundInvoice(sender, recipient);
        damageRefundInvoice.setDamageCost(mangleStringToMap(req.getParameter("damageRefund")));
        damageRefundInvoice.setTotalCost(calculateTotalCost(damageRefundInvoice.getDamageCost()));
        InvoicesOrderDtoRequest invoiceDtoReq = setupInvoiceDtoRequest(damageRefundInvoice, order);
        invoicesOrderFacade.create(invoiceDtoReq);
        UserDtoResponse user = userOrdersFacade.findUserByOrder(order.getId());
        BalanceDtoRequest balance = new BalanceDtoRequest();
        balance.setBalance(user.getBalance().subtract(new BigDecimal(damageRefundInvoice.getTotalCost())));
        userFacade.updateBalance(balance, user.getId());
    }

    private Map<String, String> mangleStringToMap(String text) {
        Map<String, String> damageCost = new HashMap<>();
        String[] split = text.split(";");
        for (String s : split) {
            String[] newSplit = s.split(",");
            damageCost.put(newSplit[0].strip(), String.valueOf(new BigDecimal(newSplit[1].strip()).doubleValue()));
        }
        return damageCost;
    }

    private String calculateTotalCost(Map<String, String> input) {
        BigDecimal cost = new BigDecimal("0.00");
        for (Map.Entry<String, String> me: input.entrySet()) {
            cost = cost.add(new BigDecimal(me.getValue()));
        }
        return cost.toPlainString();
    }
}