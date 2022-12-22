package facade.relation;

import facade.BaseFacade;
import view.dto.request.relation.InvoicesOrderDtoRequest;
import view.dto.response.relation.InvoicesOrderDtoResponse;

import java.util.List;

public interface InvoicesOrderFacade extends BaseFacade<InvoicesOrderDtoRequest, InvoicesOrderDtoResponse> {

    List<InvoicesOrderDtoResponse> findAllByOrder(Long orderId);

    List<InvoicesOrderDtoResponse> findAllByOrderFiltered(Long orderId);
}