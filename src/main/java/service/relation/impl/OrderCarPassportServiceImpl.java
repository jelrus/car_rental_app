package service.relation.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import service.relation.OrderCarPassportService;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderCarPassportServiceImpl implements OrderCarPassportService {

    private final OrderCarPassportDao orderCarPassportDao;
    private final OrderDao orderDao;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");

    public OrderCarPassportServiceImpl(OrderCarPassportDao orderCarPassportDao, OrderDao orderDao) {
        this.orderCarPassportDao = orderCarPassportDao;
        this.orderDao = orderDao;
    }

    @Override
    public Long create(OrderCarPassport orderCarPassport) {
        LOGGER_INFO.info("Relation between order, car and passport created");
        return orderCarPassportDao.create(orderCarPassport);
    }

    @Override
    public Boolean update(OrderCarPassport orderCarPassport) {
        LOGGER_INFO.info("Relation " + orderCarPassport.getId() + " between order, car and passport updated");
        return orderCarPassportDao.update(orderCarPassport);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER_WARNING.warn("Relation " + id + " between order, car and passport deleted");
        return orderCarPassportDao.delete(id);
    }

    @Override
    public OrderCarPassport findById(Long id) {
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
}