package facade.user;

import facade.BaseFacade;
import persistence.entity.user.BaseUser;
import view.dto.request.user.UserDtoRequest;
import view.dto.response.user.UserDtoResponse;

public interface BaseUserFacade extends BaseFacade<UserDtoRequest, UserDtoResponse> {

    UserDtoResponse findByUsername(String username);

    UserDtoResponse findByUsernamePassword(String username, String password);
}
