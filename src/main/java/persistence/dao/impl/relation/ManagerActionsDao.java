package persistence.dao.impl.relation;

import persistence.dao.BaseDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.ManagerActions;

public interface ManagerActionsDao extends BaseDao<ManagerActions> {

    DataTableResponse<Action> findActionsByManager(Long userId, DataTableRequest request);
}
