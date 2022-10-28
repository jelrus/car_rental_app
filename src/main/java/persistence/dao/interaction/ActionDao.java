package persistence.dao.interaction;

import persistence.dao.BaseDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;

public interface ActionDao extends BaseDao<Action> {

    Boolean updateMessage(Action action);

    Boolean updateAccess(Action action);

    DataTableResponse<Action> findAllFiltered(DataTableRequest req);
}