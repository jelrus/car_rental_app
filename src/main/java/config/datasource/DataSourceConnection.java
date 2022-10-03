package config.datasource;

import java.sql.Connection;

public interface DataSourceConnection {

    Connection getConnection();

    boolean releaseConnection(Connection connection);

    String getUrl();

    String getUser();

    String getPassword();
}
