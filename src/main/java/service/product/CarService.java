package service.product;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.product.Car;
import service.BaseService;

public interface CarService extends BaseService<Car> {

    Boolean updateAccess(Car car);

    DataTableResponse<Car> findAllFiltered(DataTableRequest req);
}