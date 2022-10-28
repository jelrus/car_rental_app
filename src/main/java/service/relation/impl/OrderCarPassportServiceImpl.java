package service.relation.impl;

import facade.interaction.OrderFacade;
import facade.interaction.impl.OrderFacadeImpl;
import persistence.dao.interaction.OrderDao;
import persistence.dao.interaction.impl.OrderDaoImpl;
import persistence.dao.relation.OrderCarPassportDao;
import persistence.dao.relation.impl.OrderCarPassportDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.interaction.Passport;
import persistence.entity.product.Car;
import persistence.entity.relation.OrderCarPassport;
import service.interaction.OrderService;
import service.interaction.impl.OrderServiceImpl;
import service.relation.OrderCarPassportService;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderCarPassportServiceImpl implements OrderCarPassportService {

    private final OrderCarPassportDao orderCarPassportDao;
    private final OrderDao orderDao;

    public OrderCarPassportServiceImpl() {
        this.orderCarPassportDao = new OrderCarPassportDaoImpl();
        this.orderDao = new OrderDaoImpl();
    }

    @Override
    public Long create(OrderCarPassport orderCarPassport) {
        /*validate(orderCarPassport);
        isExist(orderCarPassport.getId());*/
        return orderCarPassportDao.create(orderCarPassport);
    }

    @Override
    public Boolean update(OrderCarPassport orderCarPassport) {
        /*validate(orderCarPassport);
        isExist(orderCarPassport.getId());*/
        return orderCarPassportDao.update(orderCarPassport);
    }

    @Override
    public Boolean delete(Long id) {
        /*isExist(id);*/
        return orderCarPassportDao.delete(id);
    }

    @Override
    public OrderCarPassport findById(Long id) {
        /*isExist(id);*/
        return orderCarPassportDao.findById(id);
    }

    @Override
    public List<OrderCarPassport> findAll() {
        return orderCarPassportDao.findAll();
    }

    @Override
    public DataTableResponse<OrderCarPassport> findAll(DataTableRequest request) {
        DataTableResponse<OrderCarPassport> orderCarPassports = orderCarPassportDao.findAll(request);
        orderCarPassports.setItemsSize(orderCarPassportDao.count());
        return orderCarPassports;
    }

    @Override
    public Passport findPassportByOrder(Long orderId) {
        return orderCarPassportDao.findPassportByOrder(orderId);
    }

    @Override
    public Car findCarByOrder(Long orderId) {
        return orderCarPassportDao.findCarByOrder(orderId);
    }

    @Override
    public BigDecimal calculateCost(Long orderId) {
        Order order = orderDao.findById(orderId);
        Car car = orderCarPassportDao.findCarByOrder(orderId);
        return BigDecimal.valueOf(TimeUnit.DAYS.convert(order.getEnd().getTime() - order.getStart().getTime(),
                                  TimeUnit.MILLISECONDS) * car.getRentalPrice().doubleValue());
    }

    private void validate(OrderCarPassport orderCarPassport) {
        //validations
        //exception if failed
    }

    private void isExist(Long id) {
        if (!orderCarPassportDao.existById(id)) {
            throw new RuntimeException("entity not found");
        }
    }
}