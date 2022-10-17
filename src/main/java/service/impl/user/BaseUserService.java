package service.impl.user;

import persistence.entity.user.BaseUser;
import service.BaseService;

public interface BaseUserService extends BaseService<BaseUser> {

    BaseUser findByUsername(String username);

    BaseUser findByUsernamePassword(String username, String password);
}
