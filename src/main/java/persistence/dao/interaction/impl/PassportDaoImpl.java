package persistence.dao.interaction.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.interaction.PassportDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;
import util.queries.interaction.PassportQueries;
import util.resultsets.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassportDaoImpl implements PassportDao {

    private final DataSourceConnectionImpl dsc;

    public PassportDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public Long create(Passport passport) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(PassportQueries.CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(passport.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(passport.getUpdated().getTime()));
            ps.setString(3, passport.getFirstName());
            ps.setString(4, passport.getLastName());
            ps.setTimestamp(5, new Timestamp(passport.getBirthDate().getTime()));
            ps.setString(6, passport.getCountry());
            ps.setString(7, passport.getZipCode());
            ps.setString(8, passport.getRegion());
            ps.setString(9, passport.getCity());
            ps.setString(10, passport.getStreet());
            ps.setString(11, passport.getBuilding());
            ps.setString(12, passport.getPhoneNumber());
            ps.setString(13, passport.getEmail());
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
    public Boolean update(Passport passport) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(PassportQueries.UPDATE_FULL);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setString(2, passport.getFirstName());
            ps.setString(3, passport.getLastName());
            ps.setTimestamp(4, new Timestamp(passport.getBirthDate().getTime()));
            ps.setString(5, passport.getCountry());
            ps.setString(6, passport.getZipCode());
            ps.setString(7, passport.getRegion());
            ps.setString(8, passport.getCity());
            ps.setString(9, passport.getStreet());
            ps.setString(10, passport.getBuilding());
            ps.setString(11, passport.getPhoneNumber());
            ps.setString(12, passport.getEmail());
            ps.setLong(13, passport.getId());
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
            PreparedStatement ps = connection.prepareStatement(PassportQueries.DELETE);
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
            PreparedStatement ps = connection.prepareStatement(PassportQueries.EXIST);
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
            PreparedStatement ps = connection.prepareStatement(PassportQueries.FIND_BY_ID);
            ps.setLong(1, id);
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
    public List<Passport> findAll() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Passport> passports = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(PassportQueries.FIND_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                passports.add(ResultSetConverter.convertResultToPassport(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return passports;
    }

    @Override
    public DataTableResponse<Passport> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Passport> passports = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(PassportQueries.findAll(request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                passports.add(ResultSetConverter.convertResultToPassport(rs));
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
    public Integer count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(PassportQueries.COUNT);
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
}