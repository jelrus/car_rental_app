package facade.relation.impl;

import facade.relation.UserOrdersFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;
import persistence.entity.relation.OrderActions;
import persistence.entity.relation.UserOrders;
import service.impl.relation.UserOrdersService;
import service.impl.relation.impl.UserOrdersServiceImpl;
import util.DtoConverter;
import util.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.relation.UserOrdersDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.relation.OrderActionsDtoResponse;
import view.dto.response.relation.UserOrdersDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class UserOrdersFacadeImpl implements UserOrdersFacade {

    private final UserOrdersService userOrdersService;

    public UserOrdersFacadeImpl() {
        this.userOrdersService = new UserOrdersServiceImpl();
    }

    @Override
    public long create(UserOrdersDtoRequest userOrdersDtoRequest) {
        UserOrders userOrders = new UserOrders();
        userOrders.setUserId(userOrdersDtoRequest.getUserId());
        userOrders.setOrderId(userOrders.getOrderId());
        return userOrdersService.create(userOrders);
    }

    @Override
    public boolean update(UserOrdersDtoRequest userOrdersDtoRequest, Long id) {
        UserOrders userOrders = userOrdersService.findById(id);
        userOrders.setUserId(userOrdersDtoRequest.getUserId());
        userOrders.setOrderId(userOrders.getOrderId());
        return userOrdersService.update(userOrders);
    }

    @Override
    public boolean delete(Long id) {
        return userOrdersService.delete(id);
    }

    @Override
    public UserOrdersDtoResponse findById(Long id) {
        return new UserOrdersDtoResponse(userOrdersService.findById(id));
    }

    @Override
    public PageData<UserOrdersDtoResponse> findAll(HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<UserOrders> uOrders = userOrdersService.findAll(dataTableRequest);
        List<UserOrdersDtoResponse> responseList = uOrdersToDtoList(uOrders);
        PageData<UserOrdersDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(uOrders.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PageData<OrderDtoResponse> findOrdersByUser(Long userId, HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest tableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Order> orders = userOrdersService.findOrdersByUser(userId, tableRequest);
        List<OrderDtoResponse> responseList = ordersToDtoList(orders);
        PageData<OrderDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(orders.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<OrderDtoResponse> ordersToDtoList(DataTableResponse<Order> orders) {
        return orders.getItems().stream()
                                .map(OrderDtoResponse::new)
                                .collect(Collectors.toList());
    }


    private List<UserOrdersDtoResponse> uOrdersToDtoList(DataTableResponse<UserOrders> oActs) {
        return oActs.getItems().stream()
                               .map(UserOrdersDtoResponse::new)
                               .collect(Collectors.toList());
    }
}
