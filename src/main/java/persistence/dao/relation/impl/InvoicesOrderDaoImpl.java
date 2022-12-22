package persistence.dao.relation.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.relation.InvoicesOrderDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.relation.InvoicesOrder;
import util.queries.relation.InvoicesOrderQueries;
import util.queries.relation.ManagerActionsQueries;
import util.resultsets.ResultSetConverter;

import java.sql.*;
import java.util.*;

public class InvoicesOrderDaoImpl implements InvoicesOrderDao {

    private final DataSourceConnectionImpl dsc;

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public InvoicesOrderDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public Long create(InvoicesOrder invoicesOrder) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(InvoicesOrderQueries.CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(invoicesOrder.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(invoicesOrder.getUpdated().getTime()));
            ps.setString(3, invoicesOrder.getFileLink());
            ps.setLong(4, invoicesOrder.getOrderId());
            ps.setBoolean(5, invoicesOrder.getEnabled());
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
    public Boolean update(InvoicesOrder invoicesOrder) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        try {
            PreparedStatement ps = connection.prepareStatement(InvoicesOrderQueries.UPDATE_FULL,
                                                               Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(invoicesOrder.getUpdated().getTime()));
            ps.setString(2, invoicesOrder.getFileLink());
            ps.setLong(3, invoicesOrder.getOrderId());
            ps.setBoolean(4, invoicesOrder.getEnabled());
            ps.setLong(5, invoicesOrder.getId());
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
            PreparedStatement ps = connection.prepareStatement(InvoicesOrderQueries.DELETE);
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
            PreparedStatement ps = connection.prepareStatement(InvoicesOrderQueries.EXIST);
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
    public InvoicesOrder findById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        InvoicesOrder invoicesOrder = null;

        try {
            PreparedStatement ps = connection.prepareStatement(InvoicesOrderQueries.FIND_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                invoicesOrder = ResultSetConverter.convertResultToInvoicesOrder(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return invoicesOrder;
    }

    @Override
    public List<InvoicesOrder> findAll() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<InvoicesOrder> invoicesOrders = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(InvoicesOrderQueries.FIND_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                invoicesOrders.add(ResultSetConverter.convertResultToInvoicesOrder(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return invoicesOrders;
    }

    @Override
    public DataTableResponse<InvoicesOrder> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<InvoicesOrder> invoicesOrders = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(InvoicesOrderQueries.findAll(request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                invoicesOrders.add(ResultSetConverter.convertResultToInvoicesOrder(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<InvoicesOrder> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(invoicesOrders);
        tableResponse.setItemsSize(invoicesOrders.size());
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public Integer count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(InvoicesOrderQueries.COUNT);
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
    public List<InvoicesOrder> findAllByOrder(Long orderId) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<InvoicesOrder> invoicesOrders = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(InvoicesOrderQueries.FIND_ALL_BY_ORDER);
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                invoicesOrders.add(ResultSetConverter.convertResultToInvoicesOrder(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return invoicesOrders;
    }

    @Override
    public List<InvoicesOrder> findAllByOrderFiltered(Long orderId) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<InvoicesOrder> invoicesOrders = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(InvoicesOrderQueries.FIND_ALL_BY_ORDER_FILTERED);
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                invoicesOrders.add(ResultSetConverter.convertResultToInvoicesOrder(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return invoicesOrders;
    }
}