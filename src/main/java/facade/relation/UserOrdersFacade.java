package facade.relation;

import facade.BaseFacade;
import view.dto.request.relation.UserOrdersDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.relation.UserOrdersDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserOrdersFacade extends BaseFacade<UserOrdersDtoRequest, UserOrdersDtoResponse> {

    List<OrderDtoResponse> findOrdersByUser(Long userId);

    UserDtoResponse findUserByOrder(Long orderId);

    PageData<OrderDtoResponse> findOrdersByUser(Long userId, HttpServletRequest req);
}