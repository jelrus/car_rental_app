package persistence.dao.impl.product.impl;

import persistence.dao.impl.product.CarDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.product.Car;

public class CarDaoImpl implements CarDao {



    @Override
    public boolean create(Car entity) {
        return false;
    }

    @Override
    public boolean update(Car entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Car findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Car> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}