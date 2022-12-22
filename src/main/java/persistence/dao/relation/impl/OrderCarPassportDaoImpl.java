package persistence.dao.relation.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.relation.OrderCarPassportDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;
import persistence.entity.product.Car;
import persistence.entity.relation.OrderCarPassport;
import util.queries.relation.OrderCarPassportQueries;
import util.resultsets.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderCarPassportDaoImpl implements OrderCarPassportDao {

    private final DataSourceConnectionImpl dsc;

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public OrderCarPassportDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public Long create(OrderCarPassport orderCarPassport) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(OrderCarPassportQueries.CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(orderCarPassport.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(orderCarPassport.getUpdated().getTime()));
            ps.setLong(3, orderCarPassport.getOrderId());
            ps.setLong(4, orderCarPassport.getCarId());
            ps.setLong(5, orderCarPassport.getPassportId());
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
    public Boolean update(OrderCarPassport orderCarPassport) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        try {
            PreparedStatement ps = connection.prepareStatement(OrderCarPassportQueries.UPDATE_FULL, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setLong(2, orderCarPassport.getOrderId());
            ps.setLong(3, orderCarPassport.getCarId());
            ps.setLong(4, orderCarPassport.getPassportId());
            ps.setLong(5, orderCarPassport.getId());
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
            PreparedStatement ps = connection.prepareStatement(OrderCarPassportQueries.DELETE);
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
            PreparedStatement ps = connection.prepareStatement(OrderCarPassportQueries.EXIST);
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
    public OrderCarPassport findById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        OrderCarPassport orderCarPassport = new OrderCarPassport();

        try {
            PreparedStatement ps = connection.prepareStatement(OrderCarPassportQueries.FIND_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderCarPassport = ResultSetConverter.convertResultToOrderCarPassport(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return orderCarPassport;
    }

    @Override
    public List<OrderCarPassport> findAll() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<OrderCarPassport> orderCarPassports = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(OrderCarPassportQueries.FIND_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderCarPassports.add(ResultSetConverter.convertResultToOrderCarPassport(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return orderCarPassports;
    }

    @Override
    public DataTableResponse<OrderCarPassport> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<OrderCarPassport> orderCarPassports = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(OrderCarPassportQueries.findAll(request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderCarPassports.add(ResultSetConverter.convertResultToOrderCarPassport(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<OrderCarPassport> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(orderCarPassports);
        tableResponse.setItemsSize(orderCarPassports.size());
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public Integer count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(OrderCarPassportQueries.COUNT);
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
    public Passport findPassportByOrder(Long orderId) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        Passport passport = new Passport();

        try {
            PreparedStatement ps = connection.prepareStatement(OrderCarPassportQueries.FIND_PASSPORT_BY_ORDER);
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                passport = ResultSetConverter.convertResultToPassport(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return passport;
    }

    @Override
    public Car findCarByOrder(Long orderId) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        Car car = new Car();

        try {
            PreparedStatement ps = connection.prepareStatement(OrderCarPassportQueries.FIND_CAR_BY_ORDER);

            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                car = ResultSetConverter.convertResultToCar(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return car;
    }
}