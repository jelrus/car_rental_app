package persistence.dao.impl.relation;

import persistence.dao.BaseDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.product.Car;
import persistence.entity.relation.UserOrders;

public interface UserOrdersDao extends BaseDao<UserOrders> {

    DataTableResponse<Order> findOrdersByUser(Long userId, DataTableRequest request);
}