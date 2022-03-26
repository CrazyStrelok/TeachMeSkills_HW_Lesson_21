package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class DBProperties {
    private static final String PATH_DB_PROPERTIES = "src/main/java/resources/database.properties";

    public static String getDBProperties(String key) {

        try (InputStream input = new FileInputStream(PATH_DB_PROPERTIES)) {

            Properties properties = new Properties();
            properties.load(input);

            String value = properties.getProperty(key);
            return value;

        } catch (IOException io) {
            io.printStackTrace();
            return null;
        }
    }


}
