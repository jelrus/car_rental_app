package persistence.dao.impl.relation.impl;

import persistence.dao.impl.relation.OrderPassportDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.relation.OrderPassport;

public class OrderPassportDaoImpl implements OrderPassportDao {



    @Override
    public boolean create(OrderPassport entity) {
        return false;
    }

    @Override
    public boolean update(OrderPassport entity) {
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
    public OrderPassport findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<OrderPassport> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}