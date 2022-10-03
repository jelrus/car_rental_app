package persistence.dao.impl.interaction.impl;

import persistence.dao.impl.interaction.PassportDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;

public class PassportDaoImpl implements PassportDao {



    @Override
    public boolean create(Passport entity) {
        return false;
    }

    @Override
    public boolean update(Passport entity) {
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
    public Passport findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Passport> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}