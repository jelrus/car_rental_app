package facade.user;

import facade.BaseFacade;
import view.dto.request.user.*;
import view.dto.response.PageData;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.http.HttpServletRequest;

public interface UserFacade extends BaseFacade<UserDtoRequest, UserDtoResponse> {

    Long register(AuthDtoRequest authDtoRequest);

    Boolean updateSecurityInfo(AuthDtoRequest authReq, Long id);

    Boolean updateInfo(InfoDtoRequest infoReq, Long id);

    Boolean updateBalance(BalanceDtoRequest balanceReq, Long id);

    Boolean updateAccess(AccessDtoRequest accessReq, Long id);

    Boolean updateRole(RoleDtoRequest roleReq, Long id);

    UserDtoResponse findByUsernamePassword(String username, String password);

    PageData<UserDtoResponse> findAllManagers(HttpServletRequest req);

    PageData<UserDtoResponse> findAllUsers(HttpServletRequest req);
}