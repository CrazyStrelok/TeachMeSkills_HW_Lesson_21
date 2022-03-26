package service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String DATA_BASE_CONNECTIVITY_PROPERTY_FILE_PATH = "src/main/resources/database.properties";

    private static Properties properties;
    private java.sql.Connection connection;

    public DBConnection(){
        init();
    }
    public static void init(){
        try{
            properties = loadProperties();
            Class.forName(MYSQL_DRIVER_CLASS_NAME);
        }catch (ClassNotFoundException e){
            System.out.println("Unable to load MySQL Driver!");
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public java.sql.Connection getConnection() throws IOException , SQLException {
        if(connection != null && !connection.isClosed()){
            return connection;
        }
        try{
            return connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password")
            );
        }catch (SQLException e){
            System.out.println("Unable to get connection to MySQL schema");
            e.printStackTrace();
        }
        return null;
    }
    private static Properties loadProperties() throws IOException{
        Properties properties = new Properties();
        try(InputStream input = Files.newInputStream(Paths.get(DATA_BASE_CONNECTIVITY_PROPERTY_FILE_PATH))){
            properties.load(input);
        }
        return properties;
    }
}

