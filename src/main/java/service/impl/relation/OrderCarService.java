package service.impl.relation;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.product.Car;
import persistence.entity.relation.OrderCar;
import service.BaseService;

public interface OrderCarService extends BaseService<OrderCar> {

    DataTableResponse<Car> findCarByOrder(Long orderId, DataTableRequest request);
}
