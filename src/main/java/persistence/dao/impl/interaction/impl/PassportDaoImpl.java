package persistence.dao.impl.interaction.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.impl.interaction.PassportDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;

import util.QueryGenerator;

import java.sql.*;
import java.util.*;

public class PassportDaoImpl implements PassportDao {

    private final DataSourceConnectionImpl dsc;

    public PassportDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public boolean create(Passport passport) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.createQuery(Passport.class));
            ps.setTimestamp(1, new Timestamp(passport.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(passport.getUpdated().getTime()));
            ps.setString(3, passport.getFirstName());
            ps.setString(4, passport.getLastName());
            ps.setString(5, passport.getCountry());
            ps.setString(6, passport.getZipCode());
            ps.setString(7, passport.getRegion());
            ps.setString(8, passport.getCity());
            ps.setString(9, passport.getStreet());
            ps.setString(10, passport.getBuilding());
            ps.setString(11, passport.getPhoneNumber());
            ps.setString(12, passport.getEmail());

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
    public boolean update(Passport passport) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.updateQuery(Passport.class, "id",
                    List.of("created")));
            ps.setTimestamp(1, new Timestamp(passport.getUpdated().getTime()));
            ps.setString(2, passport.getFirstName());
            ps.setString(3, passport.getLastName());
            ps.setString(4, passport.getCountry());
            ps.setString(5, passport.getZipCode());
            ps.setString(6, passport.getRegion());
            ps.setString(7, passport.getCity());
            ps.setString(8, passport.getStreet());
            ps.setString(9, passport.getBuilding());
            ps.setString(10, passport.getPhoneNumber());
            ps.setString(11, passport.getEmail());
            ps.setLong(12, passport.getId());
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.deleteQuery(Passport.class, "id"));
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.existBy(Passport.class, "id"));
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
    public Passport findById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        Passport passport = null;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findBy(Passport.class, "id"));
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                passport = convertResultToPassport(rs);
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
    public DataTableResponse<Passport> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Passport> passports = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findAllByRequest(Passport.class, request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                passports.add(convertResultToPassport(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<Passport> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(passports);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public long count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.count(Passport.class));
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