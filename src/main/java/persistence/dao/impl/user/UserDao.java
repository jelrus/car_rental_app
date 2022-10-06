package persistence.dao.impl.user;

import persistence.dao.BaseDao;
import persistence.entity.user.BaseUser;

public interface UserDao extends BaseDao<BaseUser> {

    BaseUser findByUsername(String username);

    boolean existByUsername(String username);
}