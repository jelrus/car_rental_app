package service.impl.user.impl;

import persistence.dao.impl.user.UserDao;
import persistence.dao.impl.user.impl.UserDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;
import service.impl.user.UserService;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public long create(BaseUser baseUser) {
        return userDao.create(baseUser);
    }

    @Override
    public boolean update(BaseUser baseUser) {
        return userDao.update(baseUser);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public BaseUser findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public DataTableResponse<BaseUser> findAll(DataTableRequest request) {
        return userDao.findAll(request);
    }
}
