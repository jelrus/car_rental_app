package persistence.dao.relation;

import persistence.dao.BaseDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.relation.UserOrders;
import persistence.entity.user.BaseUser;

import java.util.List;

public interface UserOrdersDao extends BaseDao<UserOrders> {

    List<Order> findOrdersByUser(Long userId);

    BaseUser findUserByOrder(Long orderId);

    DataTableResponse<Order> findOrdersByUser(Long userId, DataTableRequest request);
}