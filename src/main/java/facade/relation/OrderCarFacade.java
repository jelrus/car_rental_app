package facade.relation;

import facade.BaseFacade;
import view.dto.request.relation.OrderCarDtoRequest;
import view.dto.response.PageData;
import view.dto.response.product.CarDtoResponse;
import view.dto.response.relation.OrderCarDtoResponse;

import javax.servlet.http.HttpServletRequest;

public interface OrderCarFacade extends BaseFacade<OrderCarDtoRequest, OrderCarDtoResponse> {

    PageData<CarDtoResponse> findCarByOrder(Long orderId, HttpServletRequest req);
}
