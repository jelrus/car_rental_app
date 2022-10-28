package service.interaction.impl;

import persistence.dao.interaction.OrderDao;
import persistence.dao.interaction.impl.OrderDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import service.interaction.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    public OrderServiceImpl() {
        this.orderDao = new OrderDaoImpl();
    }

    @Override
    public Long create(Order order) {
       /* validate(order);
        isExist(order.getId());*/
        return orderDao.create(order);
    }

    @Override
    public Boolean update(Order order) {
        /*validate(order);
        isExist(order.getId());*/
        return orderDao.update(order);
    }

    @Override
    public Boolean delete(Long id) {
        /*isExist(id);*/
        return orderDao.delete(id);
    }

    @Override
    public Order findById(Long id) {
        /*isExist(id);*/
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
        //validate username, password
        /*validate(order);
        isExist(order.getId());*/
        return orderDao.updateOrderStatus(order);
    }

    @Override
    public Boolean updateAccess(Order order) {
        return orderDao.updateAccess(order);
    }

    @Override
    public DataTableResponse<Order> findAllFiltered(DataTableRequest req) {
        DataTableResponse<Order> orders = orderDao.findAllFiltered(req);
        orders.setItemsSize(orderDao.count());
        return orders;
    }

    private void validate(Order order) {
        //validations
        //exception if failed
    }

    private void isExist(Long id) {
        if (!orderDao.existById(id)) {
            throw new RuntimeException("entity not found");
        }
    }
}