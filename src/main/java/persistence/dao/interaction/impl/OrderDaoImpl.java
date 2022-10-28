package persistence.dao.interaction.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.interaction.OrderDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import util.queries.interaction.ActionQueries;
import util.queries.interaction.OrderQueries;
import util.resultsets.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {

    private final DataSourceConnectionImpl dsc;

    public OrderDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public Long create(Order order) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(OrderQueries.CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(order.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(order.getUpdated().getTime()));
            ps.setBoolean(3, order.getWithDriver());
            ps.setTimestamp(4, new Timestamp(order.getStart().getTime()));
            ps.setTimestamp(5, new Timestamp(order.getEnd().getTime()));
            ps.setString(6, order.getOrderStatus().name());
            ps.setBoolean(7, order.getEnabled());
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
    public Boolean update(Order order) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(OrderQueries.UPDATE_FULL);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setBoolean(2, order.getWithDriver());
            ps.setTimestamp(3, new Timestamp(order.getStart().getTime()));
            ps.setTimestamp(4, new Timestamp(order.getEnd().getTime()));
            ps.setString(5, order.getOrderStatus().name());
            ps.setBoolean(6, order.getEnabled());
            ps.setLong(7, order.getId());
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
    public Boolean delete(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(OrderQueries.DELETE);
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
            PreparedStatement ps = connection.prepareStatement(OrderQueries.EXIST);
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
    public Order findById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        Order order = null;

        try {
            PreparedStatement ps = connection.prepareStatement(OrderQueries.FIND_BY_ID);
            ps.setLong(1, id);
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
    public List<Order> findAll() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Order> actions = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(ActionQueries.FIND_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                actions.add(ResultSetConverter.convertResultToOrder(rs));
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
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Order> orders = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(OrderQueries.findAll(request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(ResultSetConverter.convertResultToOrder(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<Order> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(orders);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public Integer count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(OrderQueries.COUNT);
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
    public Boolean updateOrderStatus(Order order) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(OrderQueries.UPDATE_STATUS);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setString(2, order.getOrderStatus().name());
            ps.setLong(3, order.getId());
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
    public Boolean updateAccess(Order order) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(OrderQueries.UPDATE_ACCESS);
            ps.setTimestamp(1, new Timestamp(order.getUpdated().getTime()));
            ps.setBoolean(2, order.getEnabled());
            ps.setLong(3, order.getId());
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
    public DataTableResponse<Order> findAllFiltered(DataTableRequest req) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Order> orders = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(OrderQueries.findAllFiltered(req));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(ResultSetConverter.convertResultToOrder(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<Order> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(orders);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public Long generateKeys(PreparedStatement ps) {
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
}