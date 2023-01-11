package facade.relation.impl;

import facade.relation.UserOrdersFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;
import persistence.entity.relation.OrderActions;
import persistence.entity.relation.UserOrders;
import service.relation.UserOrdersService;
import service.relation.impl.UserOrdersServiceImpl;
import util.facade.DtoConverter;
import util.request.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.relation.OrderActionsDtoRequest;
import view.dto.request.relation.UserOrdersDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.relation.OrderActionsDtoResponse;
import view.dto.response.relation.UserOrdersDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class UserOrdersFacadeImpl implements UserOrdersFacade {

    private final UserOrdersService userOrdersService;

    public UserOrdersFacadeImpl(UserOrdersService userOrdersService) {
        this.userOrdersService = userOrdersService;
    }

    @Override
    public Long create(UserOrdersDtoRequest dtoReq) {
        UserOrders userOrders = new UserOrders();
        userOrdersSetFields(userOrders, dtoReq);
        return userOrdersService.create(userOrders);
    }

    @Override
    public Boolean update(UserOrdersDtoRequest dtoReq, Long id) {
        UserOrders userOrders = userOrdersService.findById(id);
        userOrdersSetFields(userOrders, dtoReq);
        return userOrdersService.update(userOrders);
    }

    @Override
    public Boolean delete(Long id) {
        return userOrdersService.delete(id);
    }

    @Override
    public UserOrdersDtoResponse findById(Long id) {
        return new UserOrdersDtoResponse(userOrdersService.findById(id));
    }

    @Override
    public List<UserOrdersDtoResponse> findAll() {
        return userOrdersService.findAll().stream().map(UserOrdersDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public PageData<UserOrdersDtoResponse> findAll(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<UserOrders> userOrders = userOrdersService.findAll(dataTableRequest);
        List<UserOrdersDtoResponse> responseList = toDtoList(userOrders);
        PageData<UserOrdersDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(userOrders.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<OrderDtoResponse> findOrdersByUser(Long userId) {
        return userOrdersService.findOrdersByUser(userId).stream().map(OrderDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public UserDtoResponse findUserByOrder(Long orderId) {
        return new UserDtoResponse(userOrdersService.findUserByOrder(orderId));
    }

    @Override
    public PageData<OrderDtoResponse> findOrdersByUser(Long userId, HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Order> actions = userOrdersService.findOrdersByUser(userId, dataTableRequest);
        List<OrderDtoResponse> responseList = toOrdersDtoList(actions);
        PageData<OrderDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(actions.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private void userOrdersSetFields(UserOrders userOrders, UserOrdersDtoRequest dto) {
        userOrders.setUserId(dto.getUserId());
        userOrders.setOrderId(dto.getOrderId());
    }

    private List<UserOrdersDtoResponse> toDtoList(DataTableResponse<UserOrders> userOrders) {
        return userOrders.getItems().stream().map(UserOrdersDtoResponse::new).collect(Collectors.toList());
    }

    private List<OrderDtoResponse> toOrdersDtoList(DataTableResponse<Order> orders) {
        return orders.getItems().stream().map(OrderDtoResponse::new).collect(Collectors.toList());
    }
}