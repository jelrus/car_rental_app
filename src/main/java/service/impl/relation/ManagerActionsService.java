package service.impl.relation;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.ManagerActions;
import service.BaseService;

public interface ManagerActionsService extends BaseService<ManagerActions> {

    DataTableResponse<Action> findActionsByManager(Long userId, DataTableRequest request);
}
