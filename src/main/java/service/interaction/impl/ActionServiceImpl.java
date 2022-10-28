package service.interaction.impl;

import persistence.dao.interaction.ActionDao;
import persistence.dao.interaction.impl.ActionDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import service.interaction.ActionService;

import java.util.List;

public class ActionServiceImpl implements ActionService {

    private final ActionDao actionDao;

    public ActionServiceImpl() {
        this.actionDao = new ActionDaoImpl();
    }

    @Override
    public Long create(Action action) {
        /*validate(action);
        isExist(action.getId());*/
        return actionDao.create(action);
    }

    @Override
    public Boolean update(Action action) {
        /*validate(action);
        isExist(action.getId());*/
        return actionDao.update(action);
    }

    @Override
    public Boolean delete(Long id) {
        /*isExist(id);*/
        return actionDao.delete(id);
    }

    @Override
    public Action findById(Long id) {
        /*isExist(id);*/
        return actionDao.findById(id);
    }

    @Override
    public List<Action> findAll() {
        return actionDao.findAll();
    }

    @Override
    public DataTableResponse<Action> findAll(DataTableRequest request) {
        DataTableResponse<Action> actions = actionDao.findAll(request);
        actions.setItemsSize(actionDao.count());
        return actions;
    }

    @Override
    public Boolean updateMessage(Action action) {
        //validate username, password
        /*validate(action);
        isExist(action.getId());*/
        return actionDao.updateMessage(action);
    }

    @Override
    public Boolean updateAccess(Action action) {
        return actionDao.updateAccess(action);
    }

    @Override
    public DataTableResponse<Action> findAllFiltered(DataTableRequest req) {
        DataTableResponse<Action> actions = actionDao.findAllFiltered(req);
        actions.setItemsSize(actionDao.count());
        return actions;
    }

    private void validate(Action action) {
        //validations
        //exception if failed
    }

    private void isExist(Long id) {
        if (!actionDao.existById(id)) {
            throw new RuntimeException("entity not found");
        }
    }
}