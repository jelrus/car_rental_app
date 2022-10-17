package service.impl.relation;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.relation.UserOrders;
import service.BaseService;

public interface UserOrdersService extends BaseService<UserOrders> {

    DataTableResponse<Order> findOrdersByUser(Long userId, DataTableRequest request);
}
