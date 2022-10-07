package persistence.dao.impl.relation.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.impl.relation.OrderPassportDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;
import persistence.entity.relation.OrderPassport;
import util.QueryGenerator;

import java.sql.*;
import java.util.*;

public class OrderPassportDaoImpl implements OrderPassportDao {

    private final DataSourceConnectionImpl dsc;

    public OrderPassportDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public boolean create(OrderPassport orderPassport) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.createQuery(OrderPassport.class));
            ps.setTimestamp(1, new Timestamp(orderPassport.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(orderPassport.getUpdated().getTime()));
            ps.setLong(3, orderPassport.getOrderId());
            ps.setLong(4, orderPassport.getPassportId());
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
    public boolean update(OrderPassport orderPassport) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.updateQuery(OrderPassport.class,
                    "id", List.of("created")));
            ps.setTimestamp(1, new Timestamp(orderPassport.getUpdated().getTime()));
            ps.setLong(2, orderPassport.getOrderId());
            ps.setLong(3, orderPassport.getPassportId());
            ps.setLong(4, orderPassport.getId());
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.deleteQuery(OrderPassport.class,
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.existBy(OrderPassport.class, "id"));
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
    public OrderPassport findById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        OrderPassport orderPassport = null;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findBy(OrderPassport.class, "id"));
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderPassport = convertResultToOrderPassport(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return orderPassport;
    }

    @Override
    public DataTableResponse<OrderPassport> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<OrderPassport> orderPassport = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findAllByRequest(OrderPassport.class, request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderPassport.add(convertResultToOrderPassport(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<OrderPassport> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(orderPassport);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public long count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.count(OrderPassport.class));
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
    public DataTableResponse<Passport> findPassportByOrder(Long orderId, DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Passport> passport = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findByRelation(OrderPassport.class,
                    Passport.class,
                    "p",
                    "order_id",
                    Collections.emptyList(),
                    request));
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                passport.add(convertResultToPassport(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<Passport> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(passport);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    private OrderPassport convertResultToOrderPassport(ResultSet rs) {
        OrderPassport orderPassport = new OrderPassport();

        try {
            orderPassport.setId(rs.getLong("id"));
            orderPassport.setCreated(rs.getTimestamp("created"));
            orderPassport.setUpdated(rs.getTimestamp("updated"));
            orderPassport.setOrderId(rs.getLong("order_id"));
            orderPassport.setPassportId(rs.getLong("passport_id"));
        } catch (SQLException resEx) {
            //TODO: log error
        }

        return orderPassport;
    }

    private Passport convertResultToPassport(ResultSet rs) {
        Passport passport = new Passport();

        try {
            passport.setId(rs.getLong("id"));
            passport.setCreated(rs.getTimestamp("created"));
            passport.setUpdated(rs.getTimestamp("updated"));
            passport.setFirstName(rs.getString("first_name"));
            passport.setLastName(rs.getString("last_name"));
            passport.setCountry(rs.getString("country"));
            passport.setZipCode(rs.getString("zip_code"));
            passport.setRegion(rs.getString("region"));
            passport.setCity(rs.getString("city"));
            passport.setStreet(rs.getString("street"));
            passport.setBuilding(rs.getString("building"));
            passport.setPhoneNumber(rs.getString("phone_number"));
            passport.setEmail(rs.getString("email"));
        } catch (SQLException resEx) {
            //TODO: log error
        }

        return passport;
    }
}