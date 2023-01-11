package config.datasource.impl;

import config.datasource.DataSourceConfig;
import config.datasource.DataSourceConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataSourceConnectionImpl implements DataSourceConnection {

    private static DataSourceConfig dsc;
    private static List<Connection> connectionPool;
    private static List<Connection> usedConnections;
    private static DataSourceConnectionImpl instance;

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private DataSourceConnectionImpl(DataSourceConfig dataSourceConfig) {
        dsc = dataSourceConfig;
        connectionPool = createPool();
        usedConnections = new ArrayList<>();
    }

    public static synchronized DataSourceConnectionImpl getInstance() {
        if (instance == null) {
            instance = new DataSourceConnectionImpl(new DataSourceConfig());
        }
        return instance;
    }

    private static List<Connection> createPool() {
        List<Connection> pool = new ArrayList<>(Integer.parseInt(dsc.getMinSize()));

        for (int i = 0; i < Integer.parseInt(dsc.getMinSize()); i++) {
            pool.add(createConnection(dsc.getUrl(), dsc.getUsername(), dsc.getPassword()));
        }

        return pool;
    }

    private static Connection createConnection(String url, String user, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOGGER_ERROR.error("Connection creation failed");
        }

        return connection;
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public String getUrl() {
        return dsc.getUrl();
    }

    @Override
    public String getUser() {
        return dsc.getUsername();
    }

    @Override
    public String getPassword() {
        return dsc.getPassword();
    }

    public void setupConnection(Connection connection, int txIsoLevel) {
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(txIsoLevel);
        } catch (SQLException connSetupEx) {
            LOGGER_ERROR.error("Connection setup failed");
        }
    }

    public void rollback(Connection connection) {
        try {
            connection.rollback();
            releaseConnection(connection);
        } catch (SQLException rollbackEx) {
            LOGGER_ERROR.error("Connection rolled back");
        }
    }
}