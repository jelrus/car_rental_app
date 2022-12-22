package service.product.impl;

import exceptions.EntityNotFoundException;
import exceptions.InputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.product.CarDao;
import persistence.dao.product.impl.CarDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.product.Car;
import service.product.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {

    private final CarDao carDao;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");

    public CarServiceImpl() {
        this.carDao = new CarDaoImpl();
    }

    @Override
    public Long create(Car car) {
        LOGGER_INFO.info("Car creating started");
        validate(car);
        Long carId = carDao.create(car);
        LOGGER_INFO.info("Car " + carId +  " creating finished");
        return carId;
    }

    @Override
    public Boolean update(Car car) {
        LOGGER_INFO.info("Car " + car.getId() + " updating started");

        if (exist(car)) {
            validate(car);
            LOGGER_INFO.info("Car " + car.getId() +  " updating finished");
        }

        return carDao.update(car);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER_WARNING.warn("Car " + id +  " deleting started");

        if (exist(findById(id))) {
            LOGGER_WARNING.warn("Car " + id +  " deleting finished");
        }

        return carDao.delete(id);
    }

    @Override
    public Car findById(Long id) {
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
        LOGGER_INFO.info("Car " + car.getId() +  " access updating started");

        if (exist(car)) {
            validate(car);
            LOGGER_INFO.info("Car " + car.getId() +  " access updating finished");
        }

        return carDao.updateAccess(car);
    }

    @Override
    public DataTableResponse<Car> findAllFiltered(DataTableRequest req) {
        DataTableResponse<Car> cars = carDao.findAllFiltered(req);
        cars.setItemsSize(carDao.count());
        return cars;
    }

    @Override
    public List<Car> findAllFiltered() {
        return carDao.findAllFiltered();
    }

    private void validate(Car car) {
        if (car.getTitle().isEmpty() || car.getTitle().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank title");
            throw new InputException("Incorrect Input");
        }

        if (car.getProductPic().isEmpty() || car.getProductPic().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank product pic");
            throw new InputException("Incorrect Input");
        }

        if (car.getInfo().isEmpty() || car.getInfo().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank info");
            throw new InputException("Incorrect Input");
        }
    }

    private boolean exist(Car car) {
        if (findById(car.getId()) != null) {
            return true;
        } else {
            LOGGER_WARNING.warn("Car not found");
            throw new EntityNotFoundException("Car not found");
        }
    }
}