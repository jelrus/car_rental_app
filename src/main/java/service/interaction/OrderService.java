package service.interaction;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;
import service.BaseService;

public interface OrderService extends BaseService<Order> {

    Boolean updateOrderStatus(Order order);

    Boolean updateAccess(Order order);

    DataTableResponse<Order> findAllFiltered(DataTableRequest req);
}