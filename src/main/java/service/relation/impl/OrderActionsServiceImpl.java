package service.relation.impl;

import persistence.dao.relation.OrderActionsDao;
import persistence.dao.relation.impl.OrderActionsDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;
import persistence.entity.relation.OrderActions;
import service.relation.OrderActionsService;

import java.util.List;

public class OrderActionsServiceImpl implements OrderActionsService {

    private final OrderActionsDao orderActionsDao;

    public OrderActionsServiceImpl() {
        this.orderActionsDao = new OrderActionsDaoImpl();
    }

    @Override
    public Long create(OrderActions orderActions) {
        /*validate(orderActions);
        isExist(orderActions.getId());*/
        return orderActionsDao.create(orderActions);
    }

    @Override
    public Boolean update(OrderActions orderActions) {
        /*validate(orderActions);
        isExist(orderActions.getId());*/
        return orderActionsDao.update(orderActions);
    }

    @Override
    public Boolean delete(Long id) {
        /*isExist(id);*/
        return orderActionsDao.delete(id);
    }

    @Override
    public OrderActions findById(Long id) {
        /*isExist(id);*/
        return orderActionsDao.findById(id);
    }

    @Override
    public List<OrderActions> findAll() {
        return orderActionsDao.findAll();
    }

    @Override
    public DataTableResponse<OrderActions> findAll(DataTableRequest request) {
        DataTableResponse<OrderActions> orderActions = orderActionsDao.findAll(request);
        orderActions.setItemsSize(orderActionsDao.count());
        return orderActions;
    }

    @Override
    public List<Action> findActionsByOrder(Long orderId) {
        //validation if empty or exist?
        return orderActionsDao.findActionsByOrder(orderId);
    }

    @Override
    public List<Action> findActionsByOrderFiltered(Long orderId) {
        return orderActionsDao.findActionsByOrderFiltered(orderId);
    }

    @Override
    public Order findOrderByAction(Long actionId) {
        return orderActionsDao.findOrderByAction(actionId);
    }

    @Override
    public DataTableResponse<Action> findActionsByOrder(Long orderId, DataTableRequest request) {
        return orderActionsDao.findActionsByOrder(orderId, request);
    }

    private void validate(OrderActions orderActions) {
        //validations
        //exception if failed
    }

    private void isExist(Long id) {
        if (!orderActionsDao.existById(id)) {
            throw new RuntimeException("entity not found");
        }
    }
}