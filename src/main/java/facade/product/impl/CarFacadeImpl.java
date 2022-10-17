package facade.product.impl;

import facade.product.CarFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.product.Car;
import service.impl.product.CarService;
import service.impl.product.impl.CarServiceImpl;
import util.DtoConverter;
import util.RequestUtil;
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
    public long create(CarDtoRequest carDtoRequest) {
        Car car = new Car();
        car.setTitle(carDtoRequest.getTitle());
        car.setProductPic(carDtoRequest.getProductPic());
        car.setBrand(carDtoRequest.getBrand());
        car.setQuality(carDtoRequest.getQuality());
        car.setInfo(carDtoRequest.getInfo());
        car.setRentalPrice(carDtoRequest.getRentalPrice());
        return carService.create(car);
    }

    @Override
    public boolean update(CarDtoRequest carDtoRequest, Long id) {
        Car car = carService.findById(id);
        car.setTitle(carDtoRequest.getTitle());
        car.setProductPic(carDtoRequest.getProductPic());
        car.setBrand(carDtoRequest.getBrand());
        car.setQuality(carDtoRequest.getQuality());
        car.setInfo(carDtoRequest.getInfo());
        car.setRentalPrice(carDtoRequest.getRentalPrice());
        return carService.update(car);
    }

    @Override
    public boolean delete(Long id) {
        return carService.delete(id);
    }

    @Override
    public CarDtoResponse findById(Long id) {
        return new CarDtoResponse(carService.findById(id));
    }

    @Override
    public PageData<CarDtoResponse> findAll(HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Car> cars = carService.findAll(dataTableRequest);
        List<CarDtoResponse> responseList = toDtoList(cars);
        PageData<CarDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(cars.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<CarDtoResponse> toDtoList(DataTableResponse<Car> cars) {
        return cars.getItems().stream()
                              .map(CarDtoResponse::new)
                              .collect(Collectors.toList());
    }
}
