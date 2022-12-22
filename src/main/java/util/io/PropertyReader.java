package util.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private PropertyReader(){}

    public static Properties loadProperties(String propsFileName) {
        Properties properties = new Properties();
        InputStream propsStream = null;

        try {
            propsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propsFileName);
            properties.load(propsStream);
        } catch (IOException e) {
            LOGGER_ERROR.error("Properties loading failed");
        } finally {
            try {
                if (propsStream != null) {
                    propsStream.close();
                }
            } catch (IOException e) {
                LOGGER_ERROR.error("Properties stream failure");
            }
        }

        return properties;
    }
}