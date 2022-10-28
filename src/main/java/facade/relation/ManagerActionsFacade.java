package facade.relation;

import facade.BaseFacade;
import view.dto.request.relation.ManagerActionsDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.relation.ManagerActionsDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ManagerActionsFacade extends BaseFacade<ManagerActionsDtoRequest, ManagerActionsDtoResponse> {

    List<ActionDtoResponse> findActionsByManager(Long managerId);

    UserDtoResponse findManagerByAction(Long actionId);

    PageData<ActionDtoResponse> findActionsByManager(Long managerId, HttpServletRequest req);
}