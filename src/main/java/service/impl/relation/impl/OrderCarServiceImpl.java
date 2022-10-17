package service.impl.relation.impl;

import persistence.dao.impl.relation.OrderCarDao;
import persistence.dao.impl.relation.impl.OrderCarDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.product.Car;
import persistence.entity.relation.OrderCar;
import service.impl.relation.OrderCarService;

public class OrderCarServiceImpl implements OrderCarService {

    private final OrderCarDao orderCarDao;

    public OrderCarServiceImpl() {
        this.orderCarDao = new OrderCarDaoImpl();
    }

    @Override
    public long create(OrderCar car) {
        return orderCarDao.create(car);
    }

    @Override
    public boolean update(OrderCar car) {
        return orderCarDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        return orderCarDao.delete(id);
    }

    @Override
    public OrderCar findById(Long id) {
        return orderCarDao.findById(id);
    }

    @Override
    public DataTableResponse<OrderCar> findAll(DataTableRequest request) {
        return orderCarDao.findAll(request);
    }

    @Override
    public DataTableResponse<Car> findCarByOrder(Long orderId, DataTableRequest request) {
        return orderCarDao.findCarByOrder(orderId, request);
    }
}
