package service.interaction.impl;

import exceptions.EntityNotFoundException;
import exceptions.InputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.interaction.OrderDao;
import persistence.dao.interaction.impl.OrderDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import service.interaction.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Long create(Order order) {
        LOGGER_INFO.info("Order creating started");
        validate(order);
        Long orderId = orderDao.create(order);
        LOGGER_INFO.info("Order " + orderId +  " creating finished");
        return orderId;
    }

    @Override
    public Boolean update(Order order) {
        LOGGER_INFO.info("Order " + order.getId() + " updating started");

        if (exist(order)) {
            validate(order);
            LOGGER_INFO.info("Order " + order.getId() +  " updating finished");
        }

        return orderDao.update(order);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER_WARNING.warn("Order " + id +  " deleting started");

        if (exist(findById(id))) {
            LOGGER_WARNING.warn("Order " + id +  " deleting finished");
        }

        return orderDao.delete(id);
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        DataTableResponse<Order> orders = orderDao.findAll(request);
        orders.setItemsSize(orderDao.count());
        return orders;
    }

    @Override
    public Boolean updateOrderStatus(Order order) {
        LOGGER_INFO.info("Order " + order.getId() +  " status updating started");

        if (exist(order)) {
            validate(order);
            LOGGER_INFO.info("Order " + order.getId() +  " status updating finished");
        }

        return orderDao.updateOrderStatus(order);
    }

    @Override
    public Boolean updateAccess(Order order) {
        LOGGER_INFO.info("Order " + order.getId() +  " access updating started");

        if (exist(order)) {
            validate(order);
            LOGGER_INFO.info("Order " + order.getId() +  " access updating finished");
        }

        return orderDao.updateAccess(order);
    }

    @Override
    public DataTableResponse<Order> findAllFiltered(DataTableRequest req) {
        DataTableResponse<Order> orders = orderDao.findAllFiltered(req);
        orders.setItemsSize(orderDao.count());
        return orders;
    }

    private void validate(Order order) {
        if (order.getStart() == null) {
            LOGGER_WARNING.warn("Incorrect start date");
            throw new InputException("Incorrect Input");
        }

        if (order.getEnd() == null) {
            LOGGER_WARNING.warn("Incorrect end date");
            throw new InputException("Incorrect Input");
        }
    }

    private boolean exist(Order order) {
        if (findById(order.getId()) != null) {
            return true;
        } else {
            LOGGER_WARNING.warn("Order not found");
            throw new EntityNotFoundException("Order not found");
        }
    }
}