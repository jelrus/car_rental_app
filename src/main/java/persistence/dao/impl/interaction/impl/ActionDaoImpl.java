package persistence.dao.impl.interaction.impl;

import persistence.dao.impl.interaction.ActionDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;

public class ActionDaoImpl implements ActionDao {



    @Override
    public boolean create(Action entity) {
        return false;
    }

    @Override
    public boolean update(Action entity) {
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
    public Action findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Action> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}