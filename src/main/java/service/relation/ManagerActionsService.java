package service.relation;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.ManagerActions;
import persistence.entity.user.BaseUser;
import service.BaseService;

import java.util.List;

public interface ManagerActionsService extends BaseService<ManagerActions> {

    List<Action> findActionsByManager(Long managerId);

    BaseUser findManagerByAction(Long actionId);

    DataTableResponse<Action> findActionsByManager(Long managerId, DataTableRequest request);
}