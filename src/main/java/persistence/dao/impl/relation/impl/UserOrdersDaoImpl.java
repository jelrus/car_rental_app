package persistence.dao.impl.relation.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.impl.relation.UserOrdersDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.interaction.Passport;
import persistence.entity.interaction.type.OrderStatus;
import persistence.entity.relation.OrderPassport;
import persistence.entity.relation.UserOrders;
import util.QueryGenerator;

import java.sql.*;
import java.util.*;

public class UserOrdersDaoImpl implements UserOrdersDao {

    private final DataSourceConnectionImpl dsc;

    public UserOrdersDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public boolean create(UserOrders userOrders) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.createQuery(UserOrders.class));
            ps.setTimestamp(1, new Timestamp(userOrders.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(userOrders.getUpdated().getTime()));
            ps.setLong(3, userOrders.getUserId());
            ps.setLong(4, userOrders.getOrderId());
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
    public boolean update(UserOrders userOrders) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.updateQuery(UserOrders.class,
                    "id", List.of("created")));
            ps.setTimestamp(1, new Timestamp(userOrders.getUpdated().getTime()));
            ps.setLong(2, userOrders.getUserId());
            ps.setLong(3, userOrders.getOrderId());
            ps.setLong(4, userOrders.getId());
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.deleteQuery(UserOrders.class,
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.existBy(UserOrders.class, "id"));
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findBy(UserOrders.class, "id"));
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userOrders = convertResultToUserOrders(rs);
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
    public DataTableResponse<UserOrders> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<UserOrders> userOrders = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findAllByRequest(UserOrders.class, request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userOrders.add(convertResultToUserOrders(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<UserOrders> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(userOrders);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public long count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.count(UserOrders.class));
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
    public DataTableResponse<Order> findOrdersByUser(Long userId, DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Order> orders = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findByRelation(UserOrders.class,
                    Order.class,
                    "o",
                    "user_id",
                    Collections.emptyList(),
                    request));
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(convertResultToOrders(rs));
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

    private UserOrders convertResultToUserOrders(ResultSet rs) {
        UserOrders userOrders = new UserOrders();

        try {
            userOrders.setId(rs.getLong("id"));
            userOrders.setCreated(rs.getTimestamp("created"));
            userOrders.setUpdated(rs.getTimestamp("updated"));
            userOrders.setUserId(rs.getLong("user_id"));
            userOrders.setOrderId(rs.getLong("order_id"));
        } catch (SQLException resEx) {
            //TODO: log error
        }

        return userOrders;
    }

    private Order convertResultToOrders(ResultSet rs) {
        Order order = new Order();

        try {
            order.setId(rs.getLong("id"));
            order.setCreated(rs.getTimestamp("created"));
            order.setUpdated(rs.getTimestamp("updated"));
            order.setWithDriver(rs.getBoolean("with_driver"));
            order.setLeaseTermStart(rs.getTimestamp("lease_term_start"));
            order.setLeaseTermEnd(rs.getTimestamp("lease_term_end"));
            order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
        } catch (SQLException resEx) {
            //TODO: log error
        }

        return order;
    }
}