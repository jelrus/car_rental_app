package service.impl.relation.impl;

import persistence.dao.impl.relation.UserOrdersDao;
import persistence.dao.impl.relation.impl.UserOrdersDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.relation.UserOrders;
import service.impl.relation.UserOrdersService;

public class UserOrdersServiceImpl implements UserOrdersService {

    private final UserOrdersDao userOrdersDao;

    public UserOrdersServiceImpl() {
        this.userOrdersDao = new UserOrdersDaoImpl();
    }

    @Override
    public long create(UserOrders userOrders) {
        return userOrdersDao.create(userOrders);
    }

    @Override
    public boolean update(UserOrders userOrders) {
        return userOrdersDao.update(userOrders);
    }

    @Override
    public boolean delete(Long id) {
        return userOrdersDao.delete(id);
    }

    @Override
    public UserOrders findById(Long id) {
        return userOrdersDao.findById(id);
    }

    @Override
    public DataTableResponse<UserOrders> findAll(DataTableRequest request) {
        return userOrdersDao.findAll(request);
    }

    @Override
    public DataTableResponse<Order> findOrdersByUser(Long userId, DataTableRequest request) {
        return userOrdersDao.findOrdersByUser(userId, request);
    }
}
