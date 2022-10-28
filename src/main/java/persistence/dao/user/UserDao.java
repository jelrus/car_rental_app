package persistence.dao.user;

import persistence.dao.BaseDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;

public interface UserDao extends BaseDao<BaseUser> {

    Boolean updateSecurityInfo(BaseUser user);

    Boolean updateInfo(BaseUser user);

    Boolean updateBalance(BaseUser user);

    Boolean updateAccess(BaseUser user);

    Boolean updateRole(BaseUser user);

    Boolean existByUsernamePassword(String username, String password);

    BaseUser findByUsernamePassword(String username, String password);

    DataTableResponse<BaseUser> findAllManagers(DataTableRequest request);

    DataTableResponse<BaseUser> findAllUsers(DataTableRequest request);
}