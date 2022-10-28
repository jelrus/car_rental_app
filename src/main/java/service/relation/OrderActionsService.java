package service.relation;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;
import persistence.entity.relation.OrderActions;
import service.BaseService;

import java.util.List;

public interface OrderActionsService extends BaseService<OrderActions> {

    List<Action> findActionsByOrder(Long orderId);

    List<Action> findActionsByOrderFiltered(Long orderId);

    Order findOrderByAction(Long actionId);

    DataTableResponse<Action> findActionsByOrder(Long orderId, DataTableRequest request);
}