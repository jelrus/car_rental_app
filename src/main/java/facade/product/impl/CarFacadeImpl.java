package facade.product.impl;

import facade.product.CarFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.product.Car;
import service.product.CarService;
import service.product.impl.CarServiceImpl;
import util.facade.DtoConverter;
import util.request.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.product.CarDtoRequest;
import view.dto.response.PageData;
import view.dto.response.product.CarDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class CarFacadeImpl implements CarFacade {

    private final CarService carService;

    public CarFacadeImpl() {
        this.carService = new CarServiceImpl();
    }

    @Override
    public Long create(CarDtoRequest dtoReq) {
        Car car = new Car();
        carSetFields(car, dtoReq);
        return carService.create(car);
    }

    @Override
    public Boolean update(CarDtoRequest dtoReq, Long id) {
        Car car = carService.findById(id);
        carSetFields(car, dtoReq);
        return carService.update(car);
    }

    @Override
    public Boolean delete(Long id) {
        return carService.delete(id);
    }

    @Override
    public CarDtoResponse findById(Long id) {
        return new CarDtoResponse(carService.findById(id));
    }

    @Override
    public List<CarDtoResponse> findAll() {
        return carService.findAll().stream().map(CarDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public PageData<CarDtoResponse> findAll(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Car> cars = carService.findAll(dataTableRequest);
        List<CarDtoResponse> responseList = toDtoList(cars);
        PageData<CarDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(cars.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public Boolean updateAccess(CarDtoRequest dtoReq, Long id) {
        Car car = carService.findById(id);
        carSetFields(car, dtoReq);
        return carService.updateAccess(car);
    }

    @Override
    public PageData<CarDtoResponse> findAllFiltered(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Car> cars = carService.findAllFiltered(dataTableRequest);
        List<CarDtoResponse> responseList = toDtoList(cars);
        PageData<CarDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(cars.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private void carSetFields(Car car, CarDtoRequest dto) {
        car.setTitle(dto.getTitle());
        car.setProductPic(dto.getProductPic());
        car.setCarBrand(dto.getCarBrand());
        car.setCarQuality(dto.getCarQuality());
        car.setInfo(dto.getInfo());
        car.setRentalPrice(dto.getRentalPrice());
        car.setEnabled(dto.getEnabled());
    }

    private List<CarDtoResponse> toDtoList(DataTableResponse<Car> cars) {
        return cars.getItems().stream().map(CarDtoResponse::new).collect(Collectors.toList());
    }
}