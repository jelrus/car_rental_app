package persistence.dao.impl.relation.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.impl.interaction.impl.ActionDaoImpl;
import persistence.dao.impl.relation.ManagerActionsDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.ManagerActions;
import util.QueryGenerator;

import java.sql.*;
import java.util.*;

public class ManagerActionsDaoImpl implements ManagerActionsDao {

    private final DataSourceConnectionImpl dsc;

    public ManagerActionsDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public long create(ManagerActions managerActions) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.createQuery(ManagerActions.class),
                    Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(managerActions.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(managerActions.getUpdated().getTime()));
            ps.setLong(3, managerActions.getManagerId());
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
    public boolean update(ManagerActions managerActions) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.updateQuery(ManagerActions.class,
                    "id", List.of("created")));
            ps.setTimestamp(1, new Timestamp(managerActions.getUpdated().getTime()));
            ps.setLong(2, managerActions.getManagerId());
            ps.setLong(3, managerActions.getActionId());
            ps.setLong(4, managerActions.getId());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException updateEx) {
            dsc.rollback(connection);
            return false;
        } finally {
            dsc.releaseConnection(connection);
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.deleteQuery(ManagerActions.class,
                    "id"));
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
    public boolean existById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_UNCOMMITTED);
        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.existBy(ManagerActions.class, "id"));
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findBy(ManagerActions.class, "id"));
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                managerActions = convertResultToManagerActions(rs);
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
    public DataTableResponse<ManagerActions> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<ManagerActions> managerActions = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findAllByRequest(ManagerActions.class, request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                managerActions.add(convertResultToManagerActions(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<ManagerActions> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(managerActions);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public long count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.count(ManagerActions.class));
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
    public DataTableResponse<Action> findActionsByManager(Long orderId, DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Action> actions = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findByRelation(ManagerActions.class,
                    Action.class,
                    "a",
                    "order_id",
                    Collections.emptyList(),
                    request));
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                actions.add(convertResultToActions(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<Action> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(actions);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    private long generateKeys(PreparedStatement ps) {
        long genKey = -1;

        try (ResultSet keys = ps.getGeneratedKeys()) {
            while (keys.next()) {
                genKey = keys.getLong(1);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return genKey;
    }

    private ManagerActions convertResultToManagerActions(ResultSet rs) {
        ManagerActions managerActions = new ManagerActions();

        try {
            managerActions.setId(rs.getLong("id"));
            managerActions.setCreated(rs.getTimestamp("created"));
            managerActions.setUpdated(rs.getTimestamp("updated"));
            managerActions.setManagerId(rs.getLong("order_id"));
            managerActions.setActionId(rs.getLong("action_id"));
        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return managerActions;
    }

    private Action convertResultToActions(ResultSet rs) {
        ActionDaoImpl actionDao = new ActionDaoImpl();
        return actionDao.convertResultToAction(rs);
    }
}
