package persistence.dao.impl.user.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.impl.user.UserDao;
import persistence.dao.impl.user.impl.queries.UserQueries;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.impl.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private final DataSourceConnectionImpl dsc;

    public UserDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public boolean create(User user) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement prepCreateStatement = connection.prepareStatement(UserQueries.CREATE);
            prepCreateStatement.setTimestamp(1, new Timestamp(user.getCreated().getTime()));
            prepCreateStatement.setTimestamp(2, new Timestamp(user.getUpdated().getTime()));
            prepCreateStatement.setString(3, user.getUsername());
            prepCreateStatement.setString(4, user.getPassword());
            prepCreateStatement.setString(5, user.getFirstName());
            prepCreateStatement.setString(6, user.getLastName());
            prepCreateStatement.setString(7, user.getProfilePic());
            prepCreateStatement.setBigDecimal(8, user.getBalance());
            prepCreateStatement.setString(9, user.getDescription());
            prepCreateStatement.setBoolean(10, user.getEnabled());
            prepCreateStatement.setString(11, user.getRoleType().name());
            prepCreateStatement.executeUpdate();
            connection.commit();
        } catch (SQLException createEx) {
            dsc.rollback(connection);
            return false;
        } finally {
            dsc.releaseConnection(connection);
        }
        return true;
    }

    //TODO: update
    @Override
    public boolean update(User user) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement prepareUpdateStatement = connection.prepareStatement(UserQueries.UPDATE);
            prepareUpdateStatement.setTimestamp(1, new Timestamp(user.getUpdated().getTime()));
            prepareUpdateStatement.setString(2, user.getUsername());
            prepareUpdateStatement.setString(3, user.getPassword());
            prepareUpdateStatement.setString(4, user.getFirstName());
            prepareUpdateStatement.setString(5, user.getLastName());
            prepareUpdateStatement.setString(6, user.getProfilePic());
            prepareUpdateStatement.setBigDecimal(7, user.getBalance());
            prepareUpdateStatement.setString(8, user.getDescription());
            prepareUpdateStatement.setBoolean(9, user.getEnabled());
            prepareUpdateStatement.setString(10, user.getRoleType().name());
            prepareUpdateStatement.setLong(11, user.getId());
            prepareUpdateStatement.executeUpdate();
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
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_UNCOMMITTED);
        int count = 0;

        try {
            PreparedStatement prepareExistStatement = connection.prepareStatement(UserQueries.EXIST_BY_ID);
            prepareExistStatement.setLong(1, id);
            ResultSet result = prepareExistStatement.executeQuery();

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
    public User findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}