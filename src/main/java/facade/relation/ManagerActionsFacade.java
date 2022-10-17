package facade.relation;

import facade.BaseFacade;
import view.dto.request.relation.ManagerActionsDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.relation.ManagerActionsDtoResponse;

import javax.servlet.http.HttpServletRequest;

public interface ManagerActionsFacade extends BaseFacade<ManagerActionsDtoRequest, ManagerActionsDtoResponse> {

    PageData<ActionDtoResponse> findActionsByManager(Long userId, HttpServletRequest req);
}
