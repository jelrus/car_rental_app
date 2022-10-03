package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {

    private PropertyReader(){}

    public static Properties loadProperties(String propsFileName) {
        Properties properties = new Properties();
        InputStream propsStream = null;

        try {
            propsStream = new FileInputStream(propsFileName);
            properties.load(propsStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (propsStream != null) {
                    propsStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return properties;
    }
}
