package service.relation.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");

    public OrderActionsServiceImpl(OrderActionsDao orderActionsDao) {
        this.orderActionsDao = orderActionsDao;
    }

    @Override
    public Long create(OrderActions orderActions) {
        LOGGER_INFO.info("Relation between order and action created");
        return orderActionsDao.create(orderActions);
    }

    @Override
    public Boolean update(OrderActions orderActions) {
        LOGGER_INFO.info("Relation " + orderActions.getId() + " between order and action updated");
        return orderActionsDao.update(orderActions);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER_WARNING.warn("Relation " + id + " between order and action deleted");
        return orderActionsDao.delete(id);
    }

    @Override
    public OrderActions findById(Long id) {
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
}