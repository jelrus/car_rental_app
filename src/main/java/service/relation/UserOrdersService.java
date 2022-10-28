package service.relation;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.relation.UserOrders;
import persistence.entity.user.BaseUser;
import service.BaseService;

import java.util.List;

public interface UserOrdersService extends BaseService<UserOrders> {

    List<Order> findOrdersByUser(Long userId);

    BaseUser findUserByOrder(Long orderId);

    DataTableResponse<Order> findOrdersByUser(Long userId, DataTableRequest request);
}