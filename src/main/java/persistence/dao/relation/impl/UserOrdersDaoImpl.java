package persistence.dao.relation.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.relation.UserOrdersDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.relation.UserOrders;
import persistence.entity.user.BaseUser;
import util.queries.relation.UserOrdersQueries;
import util.resultsets.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserOrdersDaoImpl implements UserOrdersDao {

    private final DataSourceConnectionImpl dsc;

    public UserOrdersDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public Long create(UserOrders userOrders) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(UserOrdersQueries.CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(userOrders.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(userOrders.getUpdated().getTime()));
            ps.setLong(3, userOrders.getUserId());
            ps.setLong(4, userOrders.getOrderId());
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
    public Boolean update(UserOrders userOrders) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        try {
            PreparedStatement ps = connection.prepareStatement(UserOrdersQueries.UPDATE_FULL, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setLong(2, userOrders.getUserId());
            ps.setLong(3, userOrders.getOrderId());
            ps.setLong(4, userOrders.getId());
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
            PreparedStatement ps = connection.prepareStatement(UserOrdersQueries.DELETE);
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
            PreparedStatement ps = connection.prepareStatement(UserOrdersQueries.EXIST);
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
    public UserOrders findById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        UserOrders userOrders = null;

        try {
            PreparedStatement ps = connection.prepareStatement(UserOrdersQueries.FIND_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userOrders = ResultSetConverter.convertResultToUserOrders(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return userOrders;
    }

    @Override
    public List<UserOrders> findAll() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<UserOrders> userOrders = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(UserOrdersQueries.FIND_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userOrders.add(ResultSetConverter.convertResultToUserOrders(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return userOrders;
    }

    @Override
    public DataTableResponse<UserOrders> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<UserOrders> userOrders = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(UserOrdersQueries.findAll(request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userOrders.add(ResultSetConverter.convertResultToUserOrders(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<UserOrders> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(userOrders);
        tableResponse.setItemsSize(userOrders.size());
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public Integer count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(UserOrdersQueries.COUNT);
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
            sqlEx.printStackTrace();
        }

        return genKey;
    }

    @Override
    public List<Order> findOrdersByUser(Long userId) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Order> orders = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(UserOrdersQueries.FIND_ORDERS_BY_USER);
            ps.setLong(1, userId);
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

        return orders;
    }

    @Override
    public BaseUser findUserByOrder(Long orderId) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        BaseUser baseUser = null;

        try {
            PreparedStatement ps = connection.prepareStatement(UserOrdersQueries.FIND_USER_BY_ORDER);
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                baseUser = ResultSetConverter.convertResultToUser(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return baseUser;
    }

    @Override
    public DataTableResponse<Order> findOrdersByUser(Long userId, DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Order> orders = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(UserOrdersQueries.findAllOrdersByUser(request));
            ps.setLong(1, userId);
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
        tableResponse.setItemsSize(orders.size());
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }
}