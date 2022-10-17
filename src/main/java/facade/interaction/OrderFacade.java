package facade.interaction;

import facade.BaseFacade;
import view.dto.request.interaction.OrderDtoRequest;
import view.dto.response.interaction.OrderDtoResponse;

public interface OrderFacade extends BaseFacade<OrderDtoRequest, OrderDtoResponse> {}