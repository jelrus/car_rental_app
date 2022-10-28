package service.relation.impl;

import persistence.dao.relation.ManagerActionsDao;
import persistence.dao.relation.impl.ManagerActionsDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.ManagerActions;
import persistence.entity.user.BaseUser;
import service.relation.ManagerActionsService;

import java.util.List;

public class ManagerActionsServiceImpl implements ManagerActionsService {

    private final ManagerActionsDao managerActionsDao;

    public ManagerActionsServiceImpl() {
        this.managerActionsDao = new ManagerActionsDaoImpl();
    }

    @Override
    public Long create(ManagerActions managerActions) {
        /*validate(managerActions);
        isExist(managerActions.getId());*/
        return managerActionsDao.create(managerActions);
    }

    @Override
    public Boolean update(ManagerActions managerActions) {
        /*validate(managerActions);
        isExist(managerActions.getId());*/
        return managerActionsDao.update(managerActions);
    }

    @Override
    public Boolean delete(Long id) {
        /*isExist(id);*/
        return managerActionsDao.delete(id);
    }

    @Override
    public ManagerActions findById(Long id) {
        /*isExist(id);*/
        return managerActionsDao.findById(id);
    }

    @Override
    public List<ManagerActions> findAll() {
        return managerActionsDao.findAll();
    }

    @Override
    public DataTableResponse<ManagerActions> findAll(DataTableRequest request) {
        DataTableResponse<ManagerActions> managerActionsDaoAll = managerActionsDao.findAll(request);
        managerActionsDaoAll.setItemsSize(managerActionsDao.count());
        return managerActionsDaoAll;
    }

    @Override
    public List<Action> findActionsByManager(Long managerId) {
        //validation if empty or exist?
        return managerActionsDao.findActionsByManager(managerId);
    }

    @Override
    public BaseUser findManagerByAction(Long actionId) {
        //validation if empty or exist?
        return managerActionsDao.findManagerByAction(actionId);
    }

    @Override
    public DataTableResponse<Action> findActionsByManager(Long managerId, DataTableRequest request) {
        //validation if empty or exist?
        return managerActionsDao.findActionsByManager(managerId, request);
    }

    private void validate(ManagerActions managerActions) {
        //validations
        //exception if failed
    }

    private void isExist(Long id) {
        if (!managerActionsDao.existById(id)) {
            throw new RuntimeException("entity not found");
        }
    }
}