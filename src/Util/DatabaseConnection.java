package Util;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DatabaseConnection {
    private static Properties properties = new Properties();
    private Connection conn = null;

    private static class DatabaseConnectionHolder{
        private static DatabaseConnection connection = new DatabaseConnection();
    }

    private DatabaseConnection(){

    }

    public static DatabaseConnection getInstance(){
        return DatabaseConnectionHolder.connection;
    }

    public Connection getConnection(){

            try {
                //properties.load(new FileInputStream("/Users/foureverhh/AWS_Logging/resources/db.properties"));
                properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
                String driver = properties.getProperty("mysqlDriver");
                System.out.println(driver);
                String dbname = properties.getProperty("name");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");
                String host = properties.getProperty("host");
                String url = "jdbc:mysql://" + host + ":3306/" + dbname + "?useSSL=false&serverTimezone=UTC";
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
            } catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        return conn;
    }

    public List query(String sql,Object[] objects){
        List queryResult = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i+1,objects[i]);
            }
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();//get amount of rows
            while(rs.next()){
                Map rowData = new HashMap();
                for (int i =1 ; i < columnCount; i++) {
                    rowData.put(md.getColumnName(i),rs.getObject(i));
                }
                queryResult.add(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryResult;
    }

    public void close(Connection conn, PreparedStatement ps, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
