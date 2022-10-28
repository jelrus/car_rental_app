package persistence.dao.interaction;

import persistence.dao.BaseDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;

public interface OrderDao extends BaseDao<Order> {

    Boolean updateOrderStatus(Order order);

    Boolean updateAccess(Order order);

    DataTableResponse<Order> findAllFiltered(DataTableRequest req);
}