package service.user;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;
import service.BaseService;

public interface UserService extends BaseService<BaseUser> {

    Boolean updateSecurityInfo(BaseUser user);

    Boolean updateInfo(BaseUser user);

    Boolean updateBalance(BaseUser user);

    Boolean updateAccess(BaseUser user);

    Boolean updateRole(BaseUser user);

    BaseUser findByUsernamePassword(String username, String password);

    DataTableResponse<BaseUser> findAllManagers(DataTableRequest request);

    DataTableResponse<BaseUser> findAllUsers(DataTableRequest request);
}