package service.impl.user.impl;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;
import service.impl.user.AdminService;

public class AdminServiceImpl implements AdminService {

    @Override
    public long create(BaseUser admin) {
        return 1;
    }

    @Override
    public boolean update(BaseUser admin) {
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
