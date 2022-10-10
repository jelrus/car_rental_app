package service.impl.product.impl;

import persistence.dao.impl.product.CarDao;
import persistence.dao.impl.product.impl.CarDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.product.Car;
import service.impl.product.CarService;

public class CarServiceImpl implements CarService {

    private final CarDao carDao;

    public CarServiceImpl() {
        this.carDao = new CarDaoImpl();
    }

    @Override
    public long create(Car car) {
        return carDao.create(car);
    }

    @Override
    public boolean update(Car car) {
        return carDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        return carDao.delete(id);
    }

    @Override
    public Car findById(Long id) {
        return carDao.findById(id);
    }

    @Override
    public DataTableResponse<Car> findAll(DataTableRequest request) {
        return carDao.findAll(request);
    }
}
