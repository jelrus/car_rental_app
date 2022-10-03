package config.datasource;

import util.PropertyReader;

import java.util.Properties;

public class DataSourceConfig {

    private Properties properties;
    private String url;
    private String username;
    private String password;
    private String minSize;
    private String maxSize;

    public DataSourceConfig() {
        this.properties = PropertyReader.loadProperties("src/main/resources/properties/db-connection.properties");
        this.url = properties.getProperty("url");
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");
        this.minSize = properties.getProperty("min_size");
        this.maxSize = properties.getProperty("max_size");
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMinSize() {
        return minSize;
    }

    public void setMinSize(String minSize) {
        this.minSize = minSize;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }
}
