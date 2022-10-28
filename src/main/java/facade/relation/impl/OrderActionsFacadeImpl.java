package facade.relation.impl;

import facade.relation.OrderActionsFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.OrderActions;
import service.relation.OrderActionsService;
import service.relation.impl.OrderActionsServiceImpl;
import util.facade.DtoConverter;
import util.request.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.relation.OrderActionsDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.relation.OrderActionsDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class OrderActionsFacadeImpl implements OrderActionsFacade {

    private final OrderActionsService orderActionsService;

    public OrderActionsFacadeImpl() {
        this.orderActionsService = new OrderActionsServiceImpl();
    }

    @Override
    public Long create(OrderActionsDtoRequest dtoReq) {
        OrderActions orderActions = new OrderActions();
        orderActionSetFields(orderActions, dtoReq);
        return orderActionsService.create(orderActions);
    }

    @Override
    public Boolean update(OrderActionsDtoRequest dtoReq, Long id) {
        OrderActions orderActions = orderActionsService.findById(id);
        orderActionSetFields(orderActions, dtoReq);
        return orderActionsService.update(orderActions);
    }

    @Override
    public Boolean delete(Long id) {
        return orderActionsService.delete(id);
    }

    @Override
    public OrderActionsDtoResponse findById(Long id) {
        return new OrderActionsDtoResponse(orderActionsService.findById(id));
    }

    @Override
    public List<OrderActionsDtoResponse> findAll() {
        return orderActionsService.findAll().stream().map(OrderActionsDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public PageData<OrderActionsDtoResponse> findAll(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<OrderActions> orderActions = orderActionsService.findAll(dataTableRequest);
        List<OrderActionsDtoResponse> responseList = toDtoList(orderActions);
        PageData<OrderActionsDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(orderActions.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<ActionDtoResponse> findActionsByOrder(Long orderId) {
        return orderActionsService.findActionsByOrder(orderId).stream().map(ActionDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<ActionDtoResponse> findActionsByOrderFiltered(Long orderId) {
        return orderActionsService.findActionsByOrderFiltered(orderId).stream().map(ActionDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public OrderDtoResponse findOrderByAction(Long actionId) {
        return new OrderDtoResponse(orderActionsService.findOrderByAction(actionId));
    }

    @Override
    public PageData<ActionDtoResponse> findActionsByOrder(Long orderId, HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Action> actions = orderActionsService.findActionsByOrder(orderId, dataTableRequest);
        List<ActionDtoResponse> responseList = toActionsDtoList(actions);
        PageData<ActionDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(actions.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private void orderActionSetFields(OrderActions orderActions, OrderActionsDtoRequest dto) {
        orderActions.setOrderId(dto.getOrderId());
        orderActions.setActionId(dto.getActionId());
    }

    private List<OrderActionsDtoResponse> toDtoList(DataTableResponse<OrderActions> orderActions) {
        return orderActions.getItems().stream().map(OrderActionsDtoResponse::new).collect(Collectors.toList());
    }

    private List<ActionDtoResponse> toActionsDtoList(DataTableResponse<Action> actions) {
        return actions.getItems().stream().map(ActionDtoResponse::new).collect(Collectors.toList());
    }
}