package service.user.impl;

import exceptions.EntityNotFoundException;
import exceptions.InputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.user.UserDao;
import persistence.dao.user.impl.UserDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;
import service.user.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public Long create(BaseUser user) {
        LOGGER_INFO.info("User creating started");
        if (!userDao.existByUsername(user.getUsername())) {
            validate(user);
            Long userId = userDao.create(user);
            LOGGER_INFO.info("User " + userId + " creating finished");
        } else {
            LOGGER_WARNING.warn("User credentials are incorrect");
            throw new InputException("User creating failed");
        }
        return userDao.create(user);
    }

    @Override
    public Boolean update(BaseUser user) {
        LOGGER_INFO.info("User " + user.getId() +  " updating started");

        if (exist(user)) {
            validate(user);
            LOGGER_INFO.info("Action " + user.getId() + " updating finished");
        }

        return userDao.update(user);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER_WARNING.warn("User " + id +  " deleting started");

        if (exist(findById(id))) {
            LOGGER_WARNING.warn("User " + id +  " deleting finished");
        }

        return userDao.delete(id);
    }

    @Override
    public BaseUser findById(Long id) {
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
        LOGGER_INFO.info("User " + user.getId() +  " updating started");

        if (exist(user)) {
            validate(user);
            LOGGER_INFO.info("User " + user.getId() + " updating finished");
        }

        return userDao.updateSecurityInfo(user);
    }

    @Override
    public Boolean updateInfo(BaseUser user) {
        LOGGER_INFO.info("User " + user.getId() +  " info updating started");
        return userDao.updateInfo(user);
    }

    @Override
    public Boolean updateBalance(BaseUser user) {
        LOGGER_INFO.info("User " + user.getId() +  " balance updating started");
        return userDao.updateBalance(user);
    }

    @Override
    public Boolean updateAccess(BaseUser user) {
        LOGGER_INFO.info("User " + user.getId() +  " access updating started");
        return userDao.updateAccess(user);
    }

    @Override
    public Boolean updateRole(BaseUser user) {
        LOGGER_INFO.info("User " + user.getId() +  " role updating started");
        return userDao.updateRole(user);
    }

    @Override
    public BaseUser findByUsernamePassword(String username, String password) {
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

    private void validate(BaseUser baseUser) {
        if (baseUser.getUsername().isEmpty() || baseUser.getUsername().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank username");
            throw new InputException("Incorrect Input");
        }

        if (baseUser.getPassword().isEmpty() || baseUser.getPassword().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank password");
            throw new InputException("Incorrect Input");
        }
    }

    private boolean exist(BaseUser baseUser) {
        if (findById(baseUser.getId()) != null) {
            return true;
        } else {
            LOGGER_WARNING.warn("Entity not found");
            throw new EntityNotFoundException("User not found");
        }
    }
}