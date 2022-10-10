package service.impl.relation.impl;

import persistence.dao.impl.relation.OrderActionsDao;
import persistence.dao.impl.relation.impl.OrderActionsDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.relation.OrderActions;
import service.impl.relation.OrderActionsService;

public class OrderActionsServiceImpl implements OrderActionsService {

    private final OrderActionsDao orderActionsDao;

    public OrderActionsServiceImpl() {
        this.orderActionsDao = new OrderActionsDaoImpl();
    }

    @Override
    public long create(OrderActions orderActions) {
        return orderActionsDao.create(orderActions);
    }

    @Override
    public boolean update(OrderActions orderActions) {
        return orderActionsDao.update(orderActions);
    }

    @Override
    public boolean delete(Long id) {
        return orderActionsDao.delete(id);
    }

    @Override
    public OrderActions findById(Long id) {
        return orderActionsDao.findById(id);
    }

    @Override
    public DataTableResponse<OrderActions> findAll(DataTableRequest request) {
        return orderActionsDao.findAll(request);
    }
}
