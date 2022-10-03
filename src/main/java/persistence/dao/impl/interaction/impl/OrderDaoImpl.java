package persistence.dao.impl.interaction.impl;

import persistence.dao.impl.interaction.OrderDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;

public class OrderDaoImpl implements OrderDao {



    @Override
    public boolean create(Order entity) {
        return false;
    }

    @Override
    public boolean update(Order entity) {
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
    public Order findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}