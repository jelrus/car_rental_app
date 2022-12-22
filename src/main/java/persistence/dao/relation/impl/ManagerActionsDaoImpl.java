package persistence.dao.relation.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.relation.ManagerActionsDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.ManagerActions;
import persistence.entity.user.BaseUser;
import util.queries.relation.ManagerActionsQueries;
import util.resultsets.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerActionsDaoImpl implements ManagerActionsDao {

    private final DataSourceConnectionImpl dsc;

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public ManagerActionsDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public Long create(ManagerActions managerActions) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(ManagerActionsQueries.CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(managerActions.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(managerActions.getUpdated().getTime()));
            ps.setLong(3, managerActions.getUserId());
            ps.setLong(4, managerActions.getActionId());
            ps.executeUpdate();
            generatedKey = generateKeys(ps);
            connection.commit();
        } catch (SQLException createEx) {
            dsc.rollback(connection);
            return generatedKey;
        } finally {
            dsc.releaseConnection(connection);
        }

        return generatedKey;
    }

    @Override
    public Boolean update(ManagerActions managerActions) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        try {
            PreparedStatement ps = connection.prepareStatement(ManagerActionsQueries.UPDATE_FULL, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setLong(2, managerActions.getUserId());
            ps.setLong(3, managerActions.getActionId());
            ps.setLong(4, managerActions.getId());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException createEx) {
            dsc.rollback(connection);
            return false;
        } finally {
            dsc.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public Boolean delete(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(ManagerActionsQueries.DELETE);
            ps.setLong(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException onDeleteEx) {
            dsc.rollback(connection);
            return false;
        } finally {
            dsc.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public Boolean existById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_UNCOMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(ManagerActionsQueries.EXIST);
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                count = result.getInt("count");
            }

            connection.commit();
        } catch (SQLException existEx) {
            dsc.rollback(connection);
            return false;
        } finally {
            dsc.releaseConnection(connection);
        }

        return count == 1;
    }

    @Override
    public ManagerActions findById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        ManagerActions managerActions = null;

        try {
            PreparedStatement ps = connection.prepareStatement(ManagerActionsQueries.FIND_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                managerActions = ResultSetConverter.convertResultToManagerActions(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return managerActions;
    }

    @Override
    public List<ManagerActions> findAll() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<ManagerActions> managerActions = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(ManagerActionsQueries.FIND_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                managerActions.add(ResultSetConverter.convertResultToManagerActions(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return managerActions;
    }

    @Override
    public DataTableResponse<ManagerActions> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<ManagerActions> managerActions = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(ManagerActionsQueries.findAll(request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                managerActions.add(ResultSetConverter.convertResultToManagerActions(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<ManagerActions> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(managerActions);
        tableResponse.setItemsSize(managerActions.size());
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public Integer count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(ManagerActionsQueries.COUNT);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }

            connection.commit();
        } catch (SQLException e) {
            dsc.rollback(connection);
        } finally {
            dsc.rollback(connection);
        }

        return count;
    }

    @Override
    public Long generateKeys(PreparedStatement ps) {
        long genKey = -1;

        try (ResultSet keys = ps.getGeneratedKeys()) {
            while (keys.next()) {
                genKey = keys.getLong(1);
            }
        } catch (SQLException sqlEx) {
            LOGGER_ERROR.error("Key(s) generation failed");
        }

        return genKey;
    }

    @Override
    public List<Action> findActionsByManager(Long managerId) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Action> actions = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(ManagerActionsQueries.FIND_ACTIONS_BY_MANAGER);
            ps.setLong(1, managerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                actions.add(ResultSetConverter.convertResultToAction(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return actions;
    }

    @Override
    public BaseUser findManagerByAction(Long actionId) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        BaseUser user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(ManagerActionsQueries.FIND_MANAGER_BY_ACTION);
            ps.setLong(1, actionId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = ResultSetConverter.convertResultToUser(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return user;
    }

    @Override
    public DataTableResponse<Action> findActionsByManager(Long managerId, DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Action> actions = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(ManagerActionsQueries.findActionsByManager(request));
            ps.setLong(1, managerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                actions.add(ResultSetConverter.convertResultToAction(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<Action> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(actions);
        tableResponse.setItemsSize(actions.size());
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }
}