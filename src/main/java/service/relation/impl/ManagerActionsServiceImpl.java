package service.relation.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");

    public ManagerActionsServiceImpl() {
        this.managerActionsDao = new ManagerActionsDaoImpl();
    }

    @Override
    public Long create(ManagerActions managerActions) {
        LOGGER_INFO.info("Relation between manager and action created");
        return managerActionsDao.create(managerActions);
    }

    @Override
    public Boolean update(ManagerActions managerActions) {
        LOGGER_INFO.info("Relation " + managerActions.getId() + " between manager and action updated");
        return managerActionsDao.update(managerActions);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER_WARNING.warn("Relation " + id + " between manager and action deleted");
        return managerActionsDao.delete(id);
    }

    @Override
    public ManagerActions findById(Long id) {
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
        return managerActionsDao.findActionsByManager(managerId);
    }

    @Override
    public BaseUser findManagerByAction(Long actionId) {
        return managerActionsDao.findManagerByAction(actionId);
    }

    @Override
    public DataTableResponse<Action> findActionsByManager(Long managerId, DataTableRequest request) {
        return managerActionsDao.findActionsByManager(managerId, request);
    }
}