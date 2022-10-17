package facade.relation;

import facade.BaseFacade;
import view.dto.request.relation.UserOrdersDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.relation.UserOrdersDtoResponse;

import javax.servlet.http.HttpServletRequest;

public interface UserOrdersFacade extends BaseFacade<UserOrdersDtoRequest, UserOrdersDtoResponse> {

    PageData<OrderDtoResponse> findOrdersByUser(Long userId, HttpServletRequest req);
}
