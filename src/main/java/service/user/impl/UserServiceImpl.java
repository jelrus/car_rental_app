package service.user.impl;

import persistence.dao.user.UserDao;
import persistence.dao.user.impl.UserDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;
import service.user.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public Long create(BaseUser user) {
        //validate username, password, firstName, lastName, profilePic, balance, description, enabled, roleType
        /*validate(user);*/
        /*isExistByUsernamePassword(user.getUsername(), user.getPassword());*/
        return userDao.create(user);
    }

    @Override
    public Boolean update(BaseUser user) {
        //validate username, password, firstName, lastName, profilePic, balance, description, enabled, roleType
        /*validate(user);
        isExist(user.getId());*/
        return userDao.update(user);
    }

    @Override
    public Boolean delete(Long id) {
        /*isExist(id);*/
        return userDao.delete(id);
    }

    @Override
    public BaseUser findById(Long id) {
        /*isExist(id);*/
        return userDao.findById(id);
    }

    @Override
    public List<BaseUser> findAll() {
        return userDao.findAll();
    }

    @Override
    public DataTableResponse<BaseUser> findAll(DataTableRequest request) {
        DataTableResponse<BaseUser> users = userDao.findAll(request);
        users.setItemsSize(userDao.count());
        return users;
    }

    @Override
    public Boolean updateSecurityInfo(BaseUser user) {
        //validate username, password
        /*validate(user);
        isExist(user.getId());*/
        return userDao.updateSecurityInfo(user);
    }

    @Override
    public Boolean updateInfo(BaseUser user) {
        //validate firstName, lastName, profilePic, description
        /*validate(user);
        isExist(user.getId());*/
        return userDao.updateInfo(user);
    }

    @Override
    public Boolean updateBalance(BaseUser user) {
        //validate balance
        /*validate(user);
        isExist(user.getId());*/
        return userDao.updateBalance(user);
    }

    @Override
    public Boolean updateAccess(BaseUser user) {
        //validate enabled
        /*validate(user);
        isExist(user.getId());*/
        return userDao.updateAccess(user);
    }

    @Override
    public Boolean updateRole(BaseUser user) {
        //validate roleType
        /*validate(user);
        isExist(user.getId());*/
        return userDao.updateRole(user);
    }

    @Override
    public BaseUser findByUsernamePassword(String username, String password) {
        /*isExistByUsernamePassword(username, password);*/
        return userDao.findByUsernamePassword(username, password);
    }

    @Override
    public DataTableResponse<BaseUser> findAllManagers(DataTableRequest request) {
        return userDao.findAllManagers(request);
    }

    @Override
    public DataTableResponse<BaseUser> findAllUsers(DataTableRequest request) {
        return userDao.findAllUsers(request);
    }

    private void validate(BaseUser user) {
        //validations
        //exception if failed
    }

    private void isExist(Long id) {
        if (userDao.existById(id)) {
            throw new RuntimeException("already exist");
        }
    }

    private void isExistByUsernamePassword(String username, String password) {
        if (userDao.existByUsernamePassword(username, password)) {
            throw new RuntimeException("already exist");
        }
    }
}