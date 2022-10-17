package persistence.dao.impl.user;

import persistence.dao.BaseDao;
import persistence.entity.user.BaseUser;

import java.sql.PreparedStatement;

public interface UserDao extends BaseDao<BaseUser> {

    BaseUser findByUsername(String username);

    boolean existByUsername(String username);

    BaseUser findByUsernamePassword(String username, String password);
}