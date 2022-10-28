package facade.relation.impl;

import facade.relation.OrderCarPassportFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.relation.OrderCarPassport;
import service.relation.OrderCarPassportService;
import service.relation.impl.OrderCarPassportServiceImpl;
import util.facade.DtoConverter;
import util.request.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.relation.OrderCarPassportDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.PassportDtoResponse;
import view.dto.response.product.CarDtoResponse;
import view.dto.response.relation.OrderCarPassportDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderCarPassportFacadeImpl implements OrderCarPassportFacade {

    private final OrderCarPassportService orderCarPassportService;

    public OrderCarPassportFacadeImpl() {
        this.orderCarPassportService = new OrderCarPassportServiceImpl();
    }

    @Override
    public Long create(OrderCarPassportDtoRequest dtoReq) {
        OrderCarPassport orderCarPassport = new OrderCarPassport();
        orderCarPassportSetFields(orderCarPassport, dtoReq);
        return orderCarPassportService.create(orderCarPassport);
    }

    @Override
    public Boolean update(OrderCarPassportDtoRequest dtoReq, Long id) {
        OrderCarPassport orderCarPassport = orderCarPassportService.findById(id);
        orderCarPassportSetFields(orderCarPassport, dtoReq);
        return orderCarPassportService.update(orderCarPassport);
    }

    @Override
    public Boolean delete(Long id) {
        return orderCarPassportService.delete(id);
    }

    @Override
    public OrderCarPassportDtoResponse findById(Long id) {
        return new OrderCarPassportDtoResponse(orderCarPassportService.findById(id));
    }

    @Override
    public List<OrderCarPassportDtoResponse> findAll() {
        return orderCarPassportService.findAll().stream().map(OrderCarPassportDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public PageData<OrderCarPassportDtoResponse> findAll(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<OrderCarPassport> orderCarPassports = orderCarPassportService.findAll(dataTableRequest);
        List<OrderCarPassportDtoResponse> responseList = toDtoList(orderCarPassports);
        PageData<OrderCarPassportDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(orderCarPassports.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PassportDtoResponse findPassportByOrder(Long orderId) {
        return new PassportDtoResponse(orderCarPassportService.findPassportByOrder(orderId));
    }

    @Override
    public CarDtoResponse findCarByOrder(Long orderId) {
        return new CarDtoResponse(orderCarPassportService.findCarByOrder(orderId));
    }

    @Override
    public BigDecimal calculateCost(Long orderId) {
        return orderCarPassportService.calculateCost(orderId);
    }

    private void orderCarPassportSetFields(OrderCarPassport orderCarPassport, OrderCarPassportDtoRequest dto) {
        orderCarPassport.setOrderId(dto.getOrderId());
        orderCarPassport.setCarId(dto.getCarId());
        orderCarPassport.setPassportId(dto.getPassportId());
    }

    private List<OrderCarPassportDtoResponse> toDtoList(DataTableResponse<OrderCarPassport> orderCarPassport) {
        return orderCarPassport.getItems().stream().map(OrderCarPassportDtoResponse::new).collect(Collectors.toList());
    }
}