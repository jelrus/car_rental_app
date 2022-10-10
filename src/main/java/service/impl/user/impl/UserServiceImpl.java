package service.impl.user.impl;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;
import service.impl.user.UserService;

public class UserServiceImpl implements UserService {


    @Override
    public long create(BaseUser entity) {
        return 0;
    }

    @Override
    public boolean update(BaseUser entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public BaseUser findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<BaseUser> findAll(DataTableRequest request) {
        return null;
    }
}
