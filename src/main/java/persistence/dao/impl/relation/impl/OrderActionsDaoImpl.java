package persistence.dao.impl.relation.impl;

import persistence.dao.impl.relation.OrderActionsDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.relation.OrderActions;

public class OrderActionsDaoImpl implements OrderActionsDao {



    @Override
    public boolean create(OrderActions entity) {
        return false;
    }

    @Override
    public boolean update(OrderActions entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public OrderActions findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<OrderActions> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}