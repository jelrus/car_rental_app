package service.impl.interaction.impl;

import persistence.dao.impl.interaction.ActionDao;
import persistence.dao.impl.interaction.impl.ActionDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import service.impl.interaction.ActionService;

import java.util.Date;

public class ActionServiceImpl implements ActionService {

    private final ActionDao actionDao;

    public ActionServiceImpl() {
        this.actionDao = new ActionDaoImpl();
    }

    @Override
    public long create(Action action) {
        action.setIdentifier(generateIdentifier());
        return actionDao.create(action);
    }

    @Override
    public boolean update(Action action) {
        return actionDao.update(action);
    }

    @Override
    public boolean delete(Long id) {
        return actionDao.delete(id);
    }

    @Override
    public Action findById(Long id) {
        return actionDao.findById(id);
    }

    @Override
    public DataTableResponse<Action> findAll(DataTableRequest request) {
        return actionDao.findAll(request);
    }

    public String generateIdentifier() {
        return String.valueOf(new Date().getTime());
    }
}
