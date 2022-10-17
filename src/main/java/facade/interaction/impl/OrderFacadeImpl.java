package facade.interaction.impl;

import facade.interaction.OrderFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import service.impl.interaction.OrderService;
import service.impl.interaction.impl.OrderServiceImpl;
import util.DtoConverter;
import util.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.interaction.OrderDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.OrderDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;

    public OrderFacadeImpl() {
        this.orderService = new OrderServiceImpl();
    }

    @Override
    public long create(OrderDtoRequest orderDtoRequest) {
        Order order = new Order();
        order.setWithDriver(orderDtoRequest.isWithDriver());
        order.setLeaseTermStart(orderDtoRequest.getLeaseTermStart());
        order.setLeaseTermEnd(orderDtoRequest.getLeaseTermEnd());
        return orderService.create(order);
    }

    @Override
    public boolean update(OrderDtoRequest orderDtoRequest, Long id) {
        Order order = orderService.findById(id);
        order.setWithDriver(orderDtoRequest.isWithDriver());
        order.setLeaseTermStart(orderDtoRequest.getLeaseTermStart());
        order.setLeaseTermEnd(orderDtoRequest.getLeaseTermEnd());
        order.setOrderStatus(orderDtoRequest.getOrderStatus());
        return orderService.update(order);
    }

    @Override
    public boolean delete(Long id) {
        return orderService.delete(id);
    }

    @Override
    public OrderDtoResponse findById(Long id) {
        return new OrderDtoResponse(orderService.findById(id));
    }

    @Override
    public PageData<OrderDtoResponse> findAll(HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Order> orders = orderService.findAll(dataTableRequest);
        List<OrderDtoResponse> responseList = toDtoList(orders);
        PageData<OrderDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(orders.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<OrderDtoResponse> toDtoList(DataTableResponse<Order> orders) {
        return orders.getItems().stream()
                                .map(OrderDtoResponse::new)
                                .collect(Collectors.toList());
    }
}
