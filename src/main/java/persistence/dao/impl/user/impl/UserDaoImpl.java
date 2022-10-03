package persistence.dao.impl.user.impl;

import persistence.dao.impl.user.UserDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.impl.User;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public boolean update(User entity) {
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
    public User findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}