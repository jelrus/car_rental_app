package facade.relation.impl;

import facade.relation.OrderCarFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.product.Car;
import persistence.entity.relation.OrderCar;
import service.impl.relation.OrderCarService;
import service.impl.relation.impl.OrderCarServiceImpl;
import util.DtoConverter;
import util.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.relation.OrderCarDtoRequest;
import view.dto.response.PageData;
import view.dto.response.product.CarDtoResponse;
import view.dto.response.relation.OrderCarDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class OrderCarFacadeImpl implements OrderCarFacade {

    private final OrderCarService orderCarService;

    public OrderCarFacadeImpl() {
        this.orderCarService = new OrderCarServiceImpl();
    }

    @Override
    public long create(OrderCarDtoRequest orderCarDtoRequest) {
        OrderCar orderCar = new OrderCar();
        orderCar.setOrderId(orderCarDtoRequest.getOrderId());
        orderCar.setCarId(orderCarDtoRequest.getCarId());
        return orderCarService.create(orderCar);
    }

    @Override
    public boolean update(OrderCarDtoRequest orderCarDtoRequest, Long id) {
        OrderCar orderCar = orderCarService.findById(id);
        orderCar.setOrderId(orderCarDtoRequest.getOrderId());
        orderCar.setCarId(orderCarDtoRequest.getCarId());
        return orderCarService.update(orderCar);
    }

    @Override
    public boolean delete(Long id) {
        return orderCarService.delete(id);
    }

    @Override
    public OrderCarDtoResponse findById(Long id) {
        return new OrderCarDtoResponse(orderCarService.findById(id));
    }

    @Override
    public PageData<OrderCarDtoResponse> findAll(HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<OrderCar> oCar = orderCarService.findAll(dataTableRequest);
        List<OrderCarDtoResponse> responseList = oCarToDtoList(oCar);
        PageData<OrderCarDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(oCar.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PageData<CarDtoResponse> findCarByOrder(Long orderId, HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest tableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Car> cars = orderCarService.findCarByOrder(orderId, tableRequest);
        List<CarDtoResponse> responseList = carToDtoList(cars);
        PageData<CarDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(cars.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<CarDtoResponse> carToDtoList(DataTableResponse<Car> oCar) {
        return oCar.getItems().stream()
                               .map(CarDtoResponse::new)
                               .collect(Collectors.toList());
    }


    private List<OrderCarDtoResponse> oCarToDtoList(DataTableResponse<OrderCar> oCar) {
        return oCar.getItems().stream()
                              .map(OrderCarDtoResponse::new)
                              .collect(Collectors.toList());
    }
}
