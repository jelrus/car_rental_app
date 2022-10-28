package service.product.impl;

import persistence.dao.product.CarDao;
import persistence.dao.product.impl.CarDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.product.Car;
import service.product.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {

    private final CarDao carDao;

    public CarServiceImpl() {
        this.carDao = new CarDaoImpl();
    }

    @Override
    public Long create(Car car) {
        /*validate(car);
        isExist(car.getId());*/
        return carDao.create(car);
    }

    @Override
    public Boolean update(Car car) {
        /*validate(car);
        isExist(car.getId());*/
        return carDao.update(car);
    }

    @Override
    public Boolean delete(Long id) {
        /*isExist(id);*/
        return carDao.delete(id);
    }

    @Override
    public Car findById(Long id) {
        /*isExist(id);*/
        return carDao.findById(id);
    }

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public DataTableResponse<Car> findAll(DataTableRequest request) {
        DataTableResponse<Car> cars = carDao.findAll(request);
        cars.setItemsSize(carDao.count());
        return cars;
    }

    @Override
    public Boolean updateAccess(Car car) {
        return carDao.updateAccess(car);
    }

    @Override
    public DataTableResponse<Car> findAllFiltered(DataTableRequest req) {
        DataTableResponse<Car> cars = carDao.findAllFiltered(req);
        cars.setItemsSize(carDao.count());
        return cars;
    }

    private void validate(Car car) {
        //validations
        //exception if failed
    }

    private void isExist(Long id) {
        if (!carDao.existById(id)) {
            throw new RuntimeException("entity not found");
        }
    }
}