package persistence.dao.impl.relation.impl;

import persistence.dao.impl.relation.OrderCarDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.relation.OrderCar;

public class OrderCarDaoImpl implements OrderCarDao {



    @Override
    public boolean create(OrderCar entity) {
        return false;
    }

    @Override
    public boolean update(OrderCar entity) {
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
    public OrderCar findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<OrderCar> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}