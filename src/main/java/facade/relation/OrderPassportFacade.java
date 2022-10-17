package facade.relation;

import facade.BaseFacade;
import view.dto.request.relation.OrderPassportDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.PassportDtoResponse;
import view.dto.response.relation.OrderPassportDtoResponse;

import javax.servlet.http.HttpServletRequest;

public interface OrderPassportFacade extends BaseFacade<OrderPassportDtoRequest, OrderPassportDtoResponse> {

    PageData<PassportDtoResponse> findPassportByOrder(Long orderId, HttpServletRequest req);
}
