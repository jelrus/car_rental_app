package persistence.dao.impl.relation;

import persistence.dao.BaseDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.OrderActions;

public interface OrderActionsDao extends BaseDao<OrderActions> {

    DataTableResponse<Action> findActionsByOrder(Long orderId, DataTableRequest request);
}