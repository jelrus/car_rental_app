package config;

import util.PropertyReader;

import java.util.Properties;

public class DbPropertyConfig {

    private final String driverClassName;
    private final String url;
    private final String username;
    private final String password;

    public DbPropertyConfig() {
        Properties properties = PropertyReader.loadProperties("src/main/resources/properties/db-connection.properties");
        this.driverClassName = properties.getProperty("driver.class.name");
        this.url = properties.getProperty("url");
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
