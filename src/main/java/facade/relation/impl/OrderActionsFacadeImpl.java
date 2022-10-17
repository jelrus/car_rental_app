package facade.relation.impl;

import facade.relation.OrderActionsFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.ManagerActions;
import persistence.entity.relation.OrderActions;
import service.impl.relation.OrderActionsService;
import service.impl.relation.impl.OrderActionsServiceImpl;
import util.DtoConverter;
import util.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.relation.OrderActionsDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.relation.ManagerActionsDtoResponse;
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
    public long create(OrderActionsDtoRequest orderActionsDtoRequest) {
        OrderActions orderActions = new OrderActions();
        orderActions.setOrderId(orderActionsDtoRequest.getOrderId());
        orderActions.setActionId(orderActionsDtoRequest.getActionId());
        return orderActionsService.create(orderActions);
    }

    @Override
    public boolean update(OrderActionsDtoRequest orderActionsDtoRequest, Long id) {
        OrderActions orderActions = orderActionsService.findById(id);
        orderActions.setOrderId(orderActionsDtoRequest.getOrderId());
        orderActions.setActionId(orderActionsDtoRequest.getActionId());
        return orderActionsService.update(orderActions);
    }

    @Override
    public boolean delete(Long id) {
        return orderActionsService.delete(id);
    }

    @Override
    public OrderActionsDtoResponse findById(Long id) {
        return new OrderActionsDtoResponse(orderActionsService.findById(id));
    }

    @Override
    public PageData<OrderActionsDtoResponse> findAll(HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<OrderActions> oActs = orderActionsService.findAll(dataTableRequest);
        List<OrderActionsDtoResponse> responseList = oActsToDtoList(oActs);
        PageData<OrderActionsDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(oActs.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PageData<ActionDtoResponse> findActionsByOrder(Long orderId, HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest tableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Action> actions = orderActionsService.findActionsByOrder(orderId, tableRequest);
        List<ActionDtoResponse> responseList = actionsToDtoList(actions);
        PageData<ActionDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(actions.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<ActionDtoResponse> actionsToDtoList(DataTableResponse<Action> mActs) {
        return mActs.getItems().stream()
                               .map(ActionDtoResponse::new)
                               .collect(Collectors.toList());
    }


    private List<OrderActionsDtoResponse> oActsToDtoList(DataTableResponse<OrderActions> oActs) {
        return oActs.getItems().stream()
                               .map(OrderActionsDtoResponse::new)
                               .collect(Collectors.toList());
    }
}
