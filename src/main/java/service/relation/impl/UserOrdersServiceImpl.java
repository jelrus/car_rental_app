package service.relation.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.relation.UserOrdersDao;
import persistence.dao.relation.impl.UserOrdersDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.relation.UserOrders;
import persistence.entity.user.BaseUser;
import service.relation.UserOrdersService;

import java.util.List;

public class UserOrdersServiceImpl implements UserOrdersService {

    private final UserOrdersDao userOrdersDao;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");

    public UserOrdersServiceImpl() {
        this.userOrdersDao = new UserOrdersDaoImpl();
    }

    @Override
    public Long create(UserOrders userOrders) {
        LOGGER_INFO.info("Relation between user and order created");
        return userOrdersDao.create(userOrders);
    }

    @Override
    public Boolean update(UserOrders userOrders) {
        LOGGER_INFO.info("Relation " + userOrders.getId() + " between user and order updated");
        return userOrdersDao.update(userOrders);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER_WARNING.warn("Relation " + id + " between user and order deleted");
        return userOrdersDao.delete(id);
    }

    @Override
    public UserOrders findById(Long id) {
        return userOrdersDao.findById(id);
    }

    @Override
    public List<UserOrders> findAll() {
        return userOrdersDao.findAll();
    }

    @Override
    public DataTableResponse<UserOrders> findAll(DataTableRequest request) {
        DataTableResponse<UserOrders> userOrders = userOrdersDao.findAll(request);
        userOrders.setItemsSize(userOrdersDao.count());
        return userOrders;
    }

    @Override
    public List<Order> findOrdersByUser(Long userId) {
        return userOrdersDao.findOrdersByUser(userId);
    }

    @Override
    public BaseUser findUserByOrder(Long orderId) {
        return userOrdersDao.findUserByOrder(orderId);
    }

    @Override
    public DataTableResponse<Order> findOrdersByUser(Long userId, DataTableRequest request) {
        return userOrdersDao.findOrdersByUser(userId, request);
    }
}