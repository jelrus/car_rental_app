package service.impl.relation;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;
import persistence.entity.relation.OrderPassport;
import service.BaseService;

public interface OrderPassportService extends BaseService<OrderPassport> {

    DataTableResponse<Passport> findPassportByOrder(Long orderId, DataTableRequest request);
}
