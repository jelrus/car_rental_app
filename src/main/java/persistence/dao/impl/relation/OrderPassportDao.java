package persistence.dao.impl.relation;

import persistence.dao.BaseDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;
import persistence.entity.relation.OrderPassport;

public interface OrderPassportDao extends BaseDao<OrderPassport> {

    DataTableResponse<Passport> findPassportByOrder(Long orderId, DataTableRequest request);
}