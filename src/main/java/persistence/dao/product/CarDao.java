package persistence.dao.product;

import persistence.dao.BaseDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.product.Car;

public interface CarDao extends BaseDao<Car> {

    Boolean updateAccess(Car car);

    DataTableResponse<Car> findAllFiltered(DataTableRequest req);
}