package persistence.dao.user.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.user.UserDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;
import persistence.entity.user.type.RoleType;
import util.queries.user.UserQueries;
import util.resultsets.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private final DataSourceConnectionImpl dsc;

    public UserDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public Long create(BaseUser user) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(user.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(user.getUpdated().getTime()));
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getFirstName());
            ps.setString(6, user.getLastName());
            ps.setString(7, user.getProfilePic());
            ps.setBigDecimal(8, user.getBalance());
            ps.setString(9, user.getDescription());
            ps.setBoolean(10, user.getEnabled());
            ps.setString(11, user.getRoleType().name());
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
    public Boolean update(BaseUser user) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.UPDATE_FULL);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getLastName());
            ps.setString(6, user.getProfilePic());
            ps.setBigDecimal(7, user.getBalance());
            ps.setString(8, user.getDescription());
            ps.setBoolean(9, user.getEnabled());
            ps.setString(10, user.getRoleType().name());
            ps.setLong(11, user.getId());
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
            PreparedStatement ps = connection.prepareStatement(UserQueries.DELETE);
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
            PreparedStatement ps = connection.prepareStatement(UserQueries.EXIST);
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
    public BaseUser findById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        BaseUser user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.FIND_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = ResultSetConverter.convertResultToUser(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return user;
    }

    @Override
    public List<BaseUser> findAll() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<BaseUser> users = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.FIND_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(ResultSetConverter.convertResultToUser(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return users;
    }

    @Override
    public DataTableResponse<BaseUser> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<BaseUser> users = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.findAll(request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(ResultSetConverter.convertResultToUser(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<BaseUser> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(users);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public Integer count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.COUNT);
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
    public Boolean updateSecurityInfo(BaseUser user) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.UPDATE_SECURITY_INFO);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setLong(4, user.getId());

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
    public Boolean updateInfo(BaseUser user) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.UPDATE_INFO);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getProfilePic());
            ps.setString(5, user.getDescription());
            ps.setLong(6, user.getId());
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
    public Boolean updateBalance(BaseUser user) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.UPDATE_BALANCE);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setBigDecimal(2, user.getBalance());
            ps.setLong(3, user.getId());
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
    public Boolean updateAccess(BaseUser user) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.UPDATE_ACCESS);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setBoolean(2, user.getEnabled());
            ps.setLong(3, user.getId());
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
    public Boolean updateRole(BaseUser user) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.UPDATE_ROLE);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setString(2, user.getRoleType().name());
            ps.setLong(3, user.getId());
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
    public Boolean existByUsernamePassword(String username, String password) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_UNCOMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.EXIST_BY_USERNAME_PASSWORD);
            ps.setString(1, username);
            ps.setString(2, password);
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
    public BaseUser findByUsernamePassword(String username, String password) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        BaseUser user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.FIND_BY_USERNAME_PASSWORD);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = ResultSetConverter.convertResultToUser(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return user;
    }

    @Override
    public DataTableResponse<BaseUser> findAllManagers(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<BaseUser> users = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.findAllByRole(request));
            System.out.println(UserQueries.findAllByRole(request));
            ps.setString(1, RoleType.ROLE_MANAGER.name());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(ResultSetConverter.convertResultToUser(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<BaseUser> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(users);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public DataTableResponse<BaseUser> findAllUsers(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<BaseUser> users = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(UserQueries.findAllByRole(request));
            ps.setString(1, RoleType.ROLE_USER.name());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(ResultSetConverter.convertResultToUser(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<BaseUser> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(users);
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
            sqlEx.printStackTrace();
        }

        return genKey;
    }
}