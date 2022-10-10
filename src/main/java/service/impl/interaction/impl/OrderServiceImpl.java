package service.impl.interaction.impl;

import persistence.dao.impl.interaction.OrderDao;
import persistence.dao.impl.interaction.impl.OrderDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.interaction.type.OrderStatus;
import service.impl.interaction.OrderService;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    public OrderServiceImpl() {
        this.orderDao = new OrderDaoImpl();
    }

    @Override
    public long create(Order order) {
        order.setOrderStatus(OrderStatus.PROCESSING);
        return orderDao.create(order);
    }

    @Override
    public boolean update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        return orderDao.findAll(request);
    }
}
