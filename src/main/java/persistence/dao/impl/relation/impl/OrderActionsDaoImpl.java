package persistence.dao.impl.relation.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.impl.interaction.ActionDao;
import persistence.dao.impl.interaction.impl.ActionDaoImpl;
import persistence.dao.impl.relation.OrderActionsDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.OrderActions;
import util.QueryGenerator;

import java.sql.*;
import java.util.*;

public class OrderActionsDaoImpl implements OrderActionsDao {

    private final DataSourceConnectionImpl dsc;

    public OrderActionsDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public long create(OrderActions orderActions) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.createQuery(OrderActions.class),
                                                               Statement.RETURN_GENERATED_KEYS);
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
    public boolean update(OrderActions orderActions) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.updateQuery(OrderActions.class,
                    "id", List.of("created")));
            ps.setTimestamp(1, new Timestamp(orderActions.getUpdated().getTime()));
            ps.setLong(2, orderActions.getOrderId());
            ps.setLong(3, orderActions.getActionId());
            ps.setLong(4, orderActions.getId());
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.deleteQuery(OrderActions.class,
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.existBy(OrderActions.class, "id"));
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findBy(OrderActions.class, "id"));
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderActions = convertResultToOrderActions(rs);
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
    public DataTableResponse<OrderActions> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<OrderActions> orderActions = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findAllByRequest(OrderActions.class, request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderActions.add(convertResultToOrderActions(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<OrderActions> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(orderActions);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public long count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.count(OrderActions.class));
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
    public DataTableResponse<Action> findActionsByOrder(Long orderId, DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Action> actions = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findByRelation(OrderActions.class,
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

    private OrderActions convertResultToOrderActions(ResultSet rs) {
        OrderActions orderActions = new OrderActions();

        try {
            orderActions.setId(rs.getLong("id"));
            orderActions.setCreated(rs.getTimestamp("created"));
            orderActions.setUpdated(rs.getTimestamp("updated"));
            orderActions.setOrderId(rs.getLong("order_id"));
            orderActions.setActionId(rs.getLong("action_id"));
        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return orderActions;
    }

    private Action convertResultToActions(ResultSet rs) {
        ActionDaoImpl actionDao = new ActionDaoImpl();
        return actionDao.convertResultToAction(rs);
    }
}