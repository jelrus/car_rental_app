package facade.interaction;

import facade.BaseFacade;
import persistence.datatable.DataTableRequest;
import view.dto.request.interaction.ActionDtoRequest;
import view.dto.request.interaction.MessageDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;

import javax.servlet.http.HttpServletRequest;

public interface ActionFacade extends BaseFacade<ActionDtoRequest, ActionDtoResponse> {

    Boolean updateMessage(MessageDtoRequest dtoReq, Long id);

    Boolean updateAccess(ActionDtoRequest dtoReq, Long id);

    PageData<ActionDtoResponse> findAllFiltered(HttpServletRequest req);
}