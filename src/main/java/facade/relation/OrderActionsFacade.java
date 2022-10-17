package facade.relation;

import facade.BaseFacade;
import view.dto.request.relation.OrderActionsDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.relation.OrderActionsDtoResponse;

import javax.servlet.http.HttpServletRequest;

public interface OrderActionsFacade extends BaseFacade<OrderActionsDtoRequest, OrderActionsDtoResponse> {

    PageData<ActionDtoResponse> findActionsByOrder(Long orderId, HttpServletRequest req);
}
