package config.impl;

import config.DbConfig;
import config.DbPropertyConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDbConfig implements DbConfig {

    private final DbPropertyConfig dbPropertyConfig;
    private Connection connection;
    private Statement statement;

    public MySqlDbConfig(DbPropertyConfig dbPropertyConfig) {
        this.dbPropertyConfig = dbPropertyConfig;
        connect();
    }

    @Override
    public boolean connect() {
        try {
            Class.forName(dbPropertyConfig.getDriverClassName());
            connection = DriverManager.getConnection(
                    dbPropertyConfig.getUrl(),
                    dbPropertyConfig.getUsername(),
                    dbPropertyConfig.getPassword()
            );
            statement = connection.createStatement();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public Statement getStatement(){
        return statement;
    }
}
