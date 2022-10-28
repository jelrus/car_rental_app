package config.datasource;

import util.io.PropertyReader;

import java.util.Properties;

public class DataSourceConfig {

    private final String url;
    private final String username;
    private final String password;
    private final String minSize;
    private final String maxSize;

    public DataSourceConfig() {
        Properties properties = PropertyReader.loadProperties("/properties/db-connection.properties");
        /*Properties properties = PropertyReader.loadProperties("src/main/resources/properties/db-connection.properties");*/
        this.url = properties.getProperty("url");
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");
        this.minSize = properties.getProperty("min_size");
        this.maxSize = properties.getProperty("max_size");
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

    public String getMinSize() {
        return minSize;
    }

    public String getMaxSize() {
        return maxSize;
    }
}