package config;

import java.sql.Connection;
import java.sql.Statement;

public interface DbConfig {

    boolean connect();

    Connection getConnection();

    Statement getStatement();
}
