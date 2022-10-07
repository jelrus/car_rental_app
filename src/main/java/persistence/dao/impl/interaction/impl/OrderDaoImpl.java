package persistence.dao.impl.interaction.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.impl.interaction.OrderDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.interaction.type.OrderStatus;
import util.QueryGenerator;

import java.sql.*;
import java.util.*;

public class OrderDaoImpl implements OrderDao {

    private final DataSourceConnectionImpl dsc;

    public OrderDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public long create(Order order) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.createQuery(Order.class),
                                                               Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(order.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(order.getUpdated().getTime()));
            ps.setBoolean(3, order.getWithDriver());
            ps.setTimestamp(4, new Timestamp(order.getLeaseTermStart().getTime()));
            ps.setTimestamp(5, new Timestamp(order.getLeaseTermEnd().getTime()));
            ps.setString(6, order.getOrderStatus().name());
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
    public boolean update(Order order) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.updateQuery(Order.class, "id",
                    List.of("created")));
            System.out.println(QueryGenerator.updateQuery(Order.class, "id",
                    List.of("created")));
            ps.setTimestamp(1, new Timestamp(order.getUpdated().getTime()));
            ps.setBoolean(2, order.getWithDriver());
            ps.setTimestamp(3, new Timestamp(order.getLeaseTermStart().getTime()));
            ps.setTimestamp(4, new Timestamp(order.getLeaseTermEnd().getTime()));
            ps.setString(5, order.getOrderStatus().name());
            ps.setLong(6, order.getId());

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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.deleteQuery(Order.class, "id"));
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.existBy(Order.class, "id"));
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findBy(Order.class, "id"));
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                order = convertResultToOrder(rs);
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
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Order> orders = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findAllByRequest(Order.class, request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(convertResultToOrder(rs));
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
    public long count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.count(Order.class));
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

    public Order convertResultToOrder(ResultSet rs) {
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
            resEx.printStackTrace();
        }

        return order;
    }
}