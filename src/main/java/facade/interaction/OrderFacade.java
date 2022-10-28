package facade.interaction;

import facade.BaseFacade;
import view.dto.request.interaction.ActionDtoRequest;
import view.dto.request.interaction.OrderDtoRequest;
import view.dto.request.interaction.StatusDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.interaction.OrderDtoResponse;

import javax.servlet.http.HttpServletRequest;

public interface OrderFacade extends BaseFacade<OrderDtoRequest, OrderDtoResponse> {

    Boolean updateStatus(StatusDtoRequest dtoReq, Long id);

    Boolean updateAccess(OrderDtoRequest dtoReq, Long id);

    PageData<OrderDtoResponse> findAllFiltered(HttpServletRequest req);
}