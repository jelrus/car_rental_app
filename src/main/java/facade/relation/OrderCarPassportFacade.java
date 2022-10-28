package facade.relation;

import facade.BaseFacade;
import view.dto.request.relation.OrderCarPassportDtoRequest;
import view.dto.response.interaction.PassportDtoResponse;
import view.dto.response.product.CarDtoResponse;
import view.dto.response.relation.OrderCarPassportDtoResponse;

import java.math.BigDecimal;

public interface OrderCarPassportFacade extends BaseFacade<OrderCarPassportDtoRequest, OrderCarPassportDtoResponse> {

    PassportDtoResponse findPassportByOrder(Long passportId);

    CarDtoResponse findCarByOrder(Long carId);

    BigDecimal calculateCost(Long orderId);
}