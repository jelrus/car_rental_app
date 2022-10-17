package service.impl.relation;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.OrderActions;
import service.BaseService;

public interface OrderActionsService extends BaseService<OrderActions> {

    DataTableResponse<Action> findActionsByOrder(Long orderId, DataTableRequest request);
}
