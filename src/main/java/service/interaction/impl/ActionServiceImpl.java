package service.interaction.impl;

import exceptions.EntityNotFoundException;
import exceptions.InputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.interaction.ActionDao;
import persistence.dao.interaction.impl.ActionDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import service.interaction.ActionService;

import java.util.List;

public class ActionServiceImpl implements ActionService {

    private final ActionDao actionDao;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");

    public ActionServiceImpl() {
        this.actionDao = new ActionDaoImpl();
    }

    @Override
    public Long create(Action action) {
        LOGGER_INFO.info("Action creating started");
        validate(action);
        Long actionId = actionDao.create(action);
        LOGGER_INFO.info("Action " + actionId +  " creating finished");
        return actionId;
    }

    @Override
    public Boolean update(Action action) {
        LOGGER_INFO.info("Action " + action.getId() +  " updating started");

        if (exist(action)) {
            validate(action);
            LOGGER_INFO.info("Action " + action.getId() + " updating finished");
        }

        return actionDao.update(action);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER_WARNING.warn("Action " + id +  " deleting started");

        if (exist(findById(id))) {
            LOGGER_WARNING.warn("Action " + id +  " deleting finished");
        }

        return actionDao.delete(id);
    }

    @Override
    public Action findById(Long id) {
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
        LOGGER_INFO.info("Action " + action.getId() +  " message updating started");

        if (exist(action)) {
            validate(action);
            LOGGER_INFO.info("Action " + action.getId() +  " message updating finished");
        }

        return actionDao.updateMessage(action);
    }

    @Override
    public Boolean updateAccess(Action action) {
        LOGGER_INFO.info("Action " + action.getId() +  " access updating started");

        if (exist(action)) {
            validate(action);
            LOGGER_INFO.info("Action " + action.getId() +  " access updating finished");
        }

        return actionDao.updateAccess(action);
    }

    @Override
    public DataTableResponse<Action> findAllFiltered(DataTableRequest req) {
        DataTableResponse<Action> actions = actionDao.findAllFiltered(req);
        actions.setItemsSize(actionDao.count());
        return actions;
    }

    private void validate(Action action) {
        if (action.getIdentifier().isEmpty() || action.getIdentifier().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank identifier");
            throw new InputException("Incorrect Input");
        }

        if (action.getMessage().isEmpty() || action.getMessage().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank message");
            throw new InputException("Incorrect input");
        }
    }

    private boolean exist(Action action) {
        if (findById(action.getId()) != null) {
            return true;
        } else {
            LOGGER_WARNING.warn("Action not found");
            throw new EntityNotFoundException("Action not found");
        }
    }
}