package facade.relation.impl;

import facade.relation.OrderPassportFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;
import persistence.entity.product.Car;
import persistence.entity.relation.OrderCar;
import persistence.entity.relation.OrderPassport;
import service.impl.relation.OrderPassportService;
import service.impl.relation.impl.OrderPassportServiceImpl;
import util.DtoConverter;
import util.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.relation.OrderPassportDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.PassportDtoResponse;
import view.dto.response.product.CarDtoResponse;
import view.dto.response.relation.OrderCarDtoResponse;
import view.dto.response.relation.OrderPassportDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class OrderPassportFacadeImpl implements OrderPassportFacade {

    private final OrderPassportService orderPassportService;

    public OrderPassportFacadeImpl() {
        this.orderPassportService = new OrderPassportServiceImpl();
    }

    @Override
    public long create(OrderPassportDtoRequest orderPassportDtoRequest) {
        OrderPassport orderPassport = new OrderPassport();
        orderPassport.setOrderId(orderPassportDtoRequest.getOrderId());
        orderPassport.setPassportId(orderPassportDtoRequest.getPassportId());
        return orderPassportService.create(orderPassport);
    }

    @Override
    public boolean update(OrderPassportDtoRequest orderPassportDtoRequest, Long id) {
        OrderPassport orderPassport = orderPassportService.findById(id);
        orderPassport.setOrderId(orderPassportDtoRequest.getOrderId());
        orderPassport.setPassportId(orderPassportDtoRequest.getPassportId());
        return orderPassportService.update(orderPassport);
    }

    @Override
    public boolean delete(Long id) {
        return orderPassportService.delete(id);
    }

    @Override
    public OrderPassportDtoResponse findById(Long id) {
        return new OrderPassportDtoResponse(orderPassportService.findById(id));
    }

    @Override
    public PageData<OrderPassportDtoResponse> findAll(HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<OrderPassport> oPassport = orderPassportService.findAll(dataTableRequest);
        List<OrderPassportDtoResponse> responseList = oCarToDtoList(oPassport);
        PageData<OrderPassportDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(oPassport.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PageData<PassportDtoResponse> findPassportByOrder(Long orderId, HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest tableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Passport> passports = orderPassportService.findPassportByOrder(orderId, tableRequest);
        List<PassportDtoResponse> responseList = passportToDtoList(passports);
        PageData<PassportDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(passports.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<PassportDtoResponse> passportToDtoList(DataTableResponse<Passport> oPassport) {
        return oPassport.getItems().stream()
                        .map(PassportDtoResponse::new)
                        .collect(Collectors.toList());
    }


    private List<OrderPassportDtoResponse> oCarToDtoList(DataTableResponse<OrderPassport> oPassport) {
        return oPassport.getItems().stream()
                                   .map(OrderPassportDtoResponse::new)
                                   .collect(Collectors.toList());
    }
}
