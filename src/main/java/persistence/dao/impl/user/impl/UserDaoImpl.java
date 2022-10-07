package persistence.dao.impl.user.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.impl.user.UserDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;
import persistence.entity.user.type.UserRole;
import util.QueryGenerator;

import java.sql.*;
import java.util.*;

public class UserDaoImpl implements UserDao {

    private final DataSourceConnectionImpl dsc;

    public UserDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public long create(BaseUser user) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.createQuery(BaseUser.class),
                                                               Statement.RETURN_GENERATED_KEYS);
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
    public boolean update(BaseUser user) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);


        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.updateQuery(BaseUser.class, "id",
                                                                                          List.of("created")));
            ps.setTimestamp(1, new Timestamp(user.getUpdated().getTime()));
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
    public boolean delete(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.deleteQuery(BaseUser.class, "id"));
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.existBy(BaseUser.class, "id"));
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
    public boolean existByUsername(String username) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_UNCOMMITTED);
        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.existBy(BaseUser.class, "username"));
            ps.setString(1, username);
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findBy(BaseUser.class, "id"));
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = convertResultToUser(rs);
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
    public BaseUser findByUsername(String username) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        BaseUser user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findBy(BaseUser.class, "username"));
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = convertResultToUser(rs);
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
    public DataTableResponse<BaseUser> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<BaseUser> users = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findAllByRequest(BaseUser.class, request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(convertResultToUser(rs));
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
    public long count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.count(BaseUser.class));
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

    private BaseUser convertResultToUser(ResultSet rs) {
        BaseUser baseUser = new BaseUser();

        try {
            baseUser.setId(rs.getLong("id"));
            baseUser.setCreated(rs.getTimestamp("created"));
            baseUser.setUpdated(rs.getTimestamp("updated"));
            baseUser.setUsername(rs.getString("username"));
            baseUser.setPassword(rs.getString("password"));
            baseUser.setFirstName(rs.getString("first_name"));
            baseUser.setLastName(rs.getString("last_name"));
            baseUser.setProfilePic(rs.getString("profile_pic"));
            baseUser.setBalance(rs.getBigDecimal("balance"));
            baseUser.setDescription(rs.getString("description"));
            baseUser.setEnabled(rs.getBoolean("enabled"));
            baseUser.setRoleType(UserRole.valueOf(rs.getString("role_type")));
        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return baseUser;
    }
}