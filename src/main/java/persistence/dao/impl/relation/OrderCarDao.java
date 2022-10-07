package persistence.dao.impl.relation;

import persistence.dao.BaseDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.product.Car;
import persistence.entity.relation.OrderCar;

public interface OrderCarDao extends BaseDao<OrderCar> {

    DataTableResponse<Car> findCarByOrder(Long orderId, DataTableRequest request);
}