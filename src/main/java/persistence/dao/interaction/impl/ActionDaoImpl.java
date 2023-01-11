package persistence.dao.interaction.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.interaction.ActionDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import util.queries.interaction.ActionQueries;
import util.resultsets.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionDaoImpl implements ActionDao {

    private final DataSourceConnectionImpl dsc;

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public ActionDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public Long create(Action action) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(ActionQueries.CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(action.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(action.getUpdated().getTime()));
            ps.setString(3, action.getIdentifier());
            ps.setString(4, action.getMessage());
            ps.setBoolean(5, action.getEnabled());
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
    public Boolean update(Action action) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(ActionQueries.UPDATE_FULL);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setString(2, action.getIdentifier());
            ps.setString(3, action.getMessage());
            ps.setBoolean(4, action.getEnabled());
            ps.setLong(5, action.getId());
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
            PreparedStatement ps = connection.prepareStatement(ActionQueries.DELETE);
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
            PreparedStatement ps = connection.prepareStatement(ActionQueries.EXIST);
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
    public Action findById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        Action action = null;

        try {
            PreparedStatement ps = connection.prepareStatement(ActionQueries.FIND_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                action = ResultSetConverter.convertResultToAction(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return action;
    }

    @Override
    public List<Action> findAll() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Action> actions = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(ActionQueries.FIND_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                actions.add(ResultSetConverter.convertResultToAction(rs));
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
    public DataTableResponse<Action> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Action> actions = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(ActionQueries.findAll(request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                actions.add(ResultSetConverter.convertResultToAction(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<Action> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(actions);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public Integer count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(ActionQueries.COUNT);
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
    public Boolean updateMessage(Action action) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(ActionQueries.UPDATE_MESSAGE);
            ps.setTimestamp(1, new Timestamp(action.getUpdated().getTime()));
            ps.setString(2, action.getMessage());
            ps.setLong(3, action.getId());
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
    public Boolean updateAccess(Action action) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(ActionQueries.UPDATE_ACCESS);
            ps.setTimestamp(1, new Timestamp(action.getUpdated().getTime()));
            ps.setBoolean(2, action.getEnabled());
            ps.setLong(3, action.getId());
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
    public DataTableResponse<Action> findAllFiltered(DataTableRequest req) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Action> actions = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(ActionQueries.findAllFiltered(req));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                actions.add(ResultSetConverter.convertResultToAction(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<Action> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(actions);
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
            LOGGER_ERROR.error("Key(s) generation failed");
        }

        return genKey;
    }

    @Override
    public String toString() {
        return "ActionDaoImpl{" +
                "dsc=" + dsc +
                '}';
    }
}