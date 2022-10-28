package service.interaction;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import service.BaseService;

public interface ActionService extends BaseService<Action> {

    Boolean updateMessage(Action action);

    Boolean updateAccess(Action action);

    DataTableResponse<Action> findAllFiltered(DataTableRequest req);
}