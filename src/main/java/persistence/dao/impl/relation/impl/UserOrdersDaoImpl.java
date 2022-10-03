package persistence.dao.impl.relation.impl;

import persistence.dao.impl.relation.UserOrdersDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.relation.UserOrders;

public class UserOrdersDaoImpl implements UserOrdersDao {

    @Override
    public boolean create(UserOrders entity) {
        return false;
    }

    @Override
    public boolean update(UserOrders entity) {
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
    public UserOrders findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<UserOrders> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
