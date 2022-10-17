package service.impl.user.impl;

import persistence.dao.impl.user.UserDao;
import persistence.dao.impl.user.impl.UserDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;
import service.impl.user.AdminService;


public class AdminServiceImpl implements AdminService {

    private final UserDao userDao;

    public AdminServiceImpl() {
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
    public BaseUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public BaseUser findByUsernamePassword(String username, String password) {
        return userDao.findByUsernamePassword(username, password);
    }

    @Override
    public DataTableResponse<BaseUser> findAll(DataTableRequest request) {
        return userDao.findAll(request);
    }

    @Override
    public boolean muteUser(Long userId) {
        BaseUser baseUser = userDao.findById(userId);
        baseUser.setEnabled(!baseUser.getEnabled());
        return userDao.update(baseUser);
    }
}
