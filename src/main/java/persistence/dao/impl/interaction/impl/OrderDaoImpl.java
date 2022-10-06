package persistence.dao.impl.interaction.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.impl.interaction.OrderDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Order;
import persistence.entity.user.BaseUser;
import util.QueryGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;

public class OrderDaoImpl implements OrderDao {

    private final DataSourceConnectionImpl dsc;

    public OrderDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public boolean create(Order order) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.createQuery(Order.class));
            ps.setTimestamp(1, new Timestamp(order.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(order.getUpdated().getTime()));
            ps.setBoolean(3, order.getWithDriver());
            ps.setTimestamp(4, new Timestamp(order.getLeaseTermStart().getTime()));
            ps.setTimestamp(5, new Timestamp(order.getLeaseTermEnd().getTime()));
            ps.setString(6, order.getOrderStatus().name());
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
    public boolean update(Order order) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.updateQuery(Order.class, "id",
                    Collections.emptyList()));
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
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}