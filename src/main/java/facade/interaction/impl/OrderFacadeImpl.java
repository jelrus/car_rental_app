package facade.interaction.impl;

import facade.interaction.OrderFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;
import service.interaction.OrderService;
import service.interaction.impl.OrderServiceImpl;
import util.facade.DtoConverter;
import util.request.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.interaction.OrderDtoRequest;
import view.dto.request.interaction.StatusDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.OrderDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;

    public OrderFacadeImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Long create(OrderDtoRequest dtoReq) {
        Order order = new Order();
        orderSetFields(order, dtoReq);
        return orderService.create(order);
    }

    @Override
    public Boolean update(OrderDtoRequest dtoReq, Long id) {
        Order order = orderService.findById(id);
        orderSetFields(order, dtoReq);
        return orderService.update(order);
    }

    @Override
    public Boolean delete(Long id) {
        return orderService.delete(id);
    }

    @Override
    public OrderDtoResponse findById(Long id) {
        return new OrderDtoResponse(orderService.findById(id));
    }

    @Override
    public List<OrderDtoResponse> findAll() {
        return orderService.findAll().stream().map(OrderDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public PageData<OrderDtoResponse> findAll(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Order> orders = orderService.findAll(dataTableRequest);
        List<OrderDtoResponse> responseList = toDtoList(orders);
        PageData<OrderDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(orders.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public Boolean updateStatus(StatusDtoRequest dtoReq, Long id) {
        Order order = orderService.findById(id);
        order.setOrderStatus(dtoReq.getOrderStatus());
        return orderService.updateOrderStatus(order);
    }

    @Override
    public Boolean updateAccess(OrderDtoRequest dtoReq, Long id) {
        Order order = orderService.findById(id);
        orderSetFields(order, dtoReq);
        return orderService.updateAccess(order);
    }

    @Override
    public PageData<OrderDtoResponse> findAllFiltered(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Order> orders = orderService.findAllFiltered(dataTableRequest);
        List<OrderDtoResponse> responseList = toDtoList(orders);
        PageData<OrderDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(orders.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private void orderSetFields(Order order, OrderDtoRequest dto) {
        order.setWithDriver(dto.getWithDriver());
        order.setStart(dto.getStart());
        order.setEnd(dto.getEnd());
        order.setOrderStatus(dto.getOrderStatus());
        order.setEnabled(dto.getEnabled());
    }

    private List<OrderDtoResponse> toDtoList(DataTableResponse<Order> actions) {
        return actions.getItems().stream().map(OrderDtoResponse::new).collect(Collectors.toList());
    }
}