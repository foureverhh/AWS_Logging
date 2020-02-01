package Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Properties properties = new Properties();

    public static Connection getConnection(){
        Connection conn = null;

        try {
            //properties.load(new FileInputStream("/Users/foureverhh/AWS_Logging/resources/db.properties"));
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
            String driver = properties.getProperty("mysqlDriver");
            System.out.println(driver);
            String dbname = properties.getProperty("name");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String host = properties.getProperty("host");
            String url = "jdbc:mysql://"+host+":3306/"+dbname+"?useSSL=false&serverTimezone=UTC";
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


}
