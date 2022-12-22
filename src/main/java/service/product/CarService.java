package service.product;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.product.Car;
import service.BaseService;

import java.util.List;

public interface CarService extends BaseService<Car> {

    Boolean updateAccess(Car car);

    DataTableResponse<Car> findAllFiltered(DataTableRequest req);

    List<Car> findAllFiltered();
}