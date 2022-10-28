package facade.product;

import facade.BaseFacade;
import view.dto.request.interaction.OrderDtoRequest;
import view.dto.request.product.CarDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.OrderDtoResponse;
import view.dto.response.product.CarDtoResponse;

import javax.servlet.http.HttpServletRequest;

public interface CarFacade extends BaseFacade<CarDtoRequest, CarDtoResponse> {

    Boolean updateAccess(CarDtoRequest dtoReq, Long id);

    PageData<CarDtoResponse> findAllFiltered(HttpServletRequest req);
}