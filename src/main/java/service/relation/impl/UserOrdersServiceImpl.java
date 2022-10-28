package service.relation.impl;

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

    public UserOrdersServiceImpl() {
        this.userOrdersDao = new UserOrdersDaoImpl();
    }

    @Override
    public Long create(UserOrders userOrders) {
        /*validate(userOrders);
        isExist(userOrders.getId());*/
        return userOrdersDao.create(userOrders);
    }

    @Override
    public Boolean update(UserOrders userOrders) {
        /*validate(userOrders);
        isExist(userOrders.getId());*/
        return userOrdersDao.update(userOrders);
    }

    @Override
    public Boolean delete(Long id) {
        /*isExist(id);*/
        return userOrdersDao.delete(id);
    }

    @Override
    public UserOrders findById(Long id) {
        /*isExist(id);*/
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
        //validation if empty or exist?
        return userOrdersDao.findOrdersByUser(userId);
    }

    @Override
    public BaseUser findUserByOrder(Long orderId) {
        //validation if empty or exist?
        return userOrdersDao.findUserByOrder(orderId);
    }

    @Override
    public DataTableResponse<Order> findOrdersByUser(Long userId, DataTableRequest request) {
        //validation if empty or exist?
        return userOrdersDao.findOrdersByUser(userId, request);
    }

    private void validate(UserOrders userOrders) {
        //validations
        //exception if failed
    }

    private void isExist(Long id) {
        if (!userOrdersDao.existById(id)) {
            throw new RuntimeException("entity not found");
        }
    }
}