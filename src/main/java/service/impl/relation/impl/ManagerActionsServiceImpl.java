package service.impl.relation.impl;

import persistence.dao.impl.relation.ManagerActionsDao;
import persistence.dao.impl.relation.impl.ManagerActionsDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.relation.ManagerActions;
import service.impl.relation.ManagerActionsService;

public class ManagerActionsServiceImpl implements ManagerActionsService {

    private final ManagerActionsDao managerActionsDao;

    public ManagerActionsServiceImpl() {
        this.managerActionsDao = new ManagerActionsDaoImpl();
    }

    @Override
    public long create(ManagerActions managerActions) {
        return managerActionsDao.create(managerActions);
    }

    @Override
    public boolean update(ManagerActions managerActions) {
        return managerActionsDao.update(managerActions);
    }

    @Override
    public boolean delete(Long id) {
        return managerActionsDao.delete(id);
    }

    @Override
    public ManagerActions findById(Long id) {
        return managerActionsDao.findById(id);
    }

    @Override
    public DataTableResponse<ManagerActions> findAll(DataTableRequest request) {
        return managerActionsDao.findAll(request);
    }
}
