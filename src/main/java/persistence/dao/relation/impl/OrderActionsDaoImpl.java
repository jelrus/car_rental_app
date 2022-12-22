package persistence.dao.relation.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.relation.OrderActionsDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.interaction.Order;
import persistence.entity.relation.OrderActions;
import util.queries.relation.OrderActionsQueries;
import util.resultsets.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActionsDaoImpl implements OrderActionsDao {

    private final DataSourceConnectionImpl dsc;

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public OrderActionsDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public Long create(OrderActions orderActions) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(OrderActionsQueries.CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(orderActions.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(orderActions.getUpdated().getTime()));
            ps.setLong(3, orderActions.getOrderId());
            ps.setLong(4, orderActions.getActionId());
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
    public Boolean update(OrderActions orderActions) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        try {
            PreparedStatement ps = connection.prepareStatement(OrderActionsQueries.UPDATE_FULL, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setLong(2, orderActions.getOrderId());
            ps.setLong(3, orderActions.getActionId());
            ps.setLong(4, orderActions.getId());
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
            PreparedStatement ps = connection.prepareStatement(OrderActionsQueries.DELETE);
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
            PreparedStatement ps = connection.prepareStatement(OrderActionsQueries.EXIST);
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
    public OrderActions findById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        OrderActions orderActions = null;

        try {
            PreparedStatement ps = connection.prepareStatement(OrderActionsQueries.FIND_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderActions = ResultSetConverter.convertResultToOrderActions(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return orderActions;
    }

    @Override
    public List<OrderActions> findAll() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<OrderActions> orderActions = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(OrderActionsQueries.FIND_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderActions.add(ResultSetConverter.convertResultToOrderActions(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return orderActions;
    }

    @Override
    public DataTableResponse<OrderActions> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<OrderActions> orderActions = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(OrderActionsQueries.findAll(request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderActions.add(ResultSetConverter.convertResultToOrderActions(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<OrderActions> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(orderActions);
        tableResponse.setItemsSize(orderActions.size());
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public Integer count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(OrderActionsQueries.COUNT);
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
    public List<Action> findActionsByOrder(Long orderId) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Action> actions = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(OrderActionsQueries.FIND_ACTIONS_BY_ORDER);
            ps.setLong(1, orderId);
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
    public List<Action> findActionsByOrderFiltered(Long orderId) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Action> actions = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(OrderActionsQueries.FIND_ACTIONS_BY_ORDER_FILTERED);
            ps.setLong(1, orderId);
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
    public Order findOrderByAction(Long actionId) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        Order order = null;

        try {
            PreparedStatement ps = connection.prepareStatement(OrderActionsQueries.FIND_ORDER_BY_ACTION);
            ps.setLong(1, actionId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                order = ResultSetConverter.convertResultToOrder(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return order;
    }

    @Override
    public DataTableResponse<Action> findActionsByOrder(Long orderId, DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Action> actions = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(OrderActionsQueries.findAllActionsByOrder(request));
            ps.setLong(1, orderId);
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