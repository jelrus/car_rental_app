package facade.relation;

import facade.BaseFacade;
import view.dto.request.relation.OrderActionsDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.relation.OrderActionsDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderActionsFacade extends BaseFacade<OrderActionsDtoRequest, OrderActionsDtoResponse> {

    List<ActionDtoResponse> findActionsByOrder(Long orderId);

    List<ActionDtoResponse> findActionsByOrderFiltered(Long orderId);

    OrderDtoResponse findOrderByAction(Long actionId);

    PageData<ActionDtoResponse> findActionsByOrder(Long orderId, HttpServletRequest req);
}