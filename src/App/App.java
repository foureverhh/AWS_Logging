package App;

import Util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM person";
        try {
             ps = conn.prepareCall(sql);
             rs = ps.executeQuery();
             while(rs.next()){
                 System.out.println(rs.getString(1) + "-->" +
                         rs.getString(2) + "-->" +
                         rs.getString(3) + "-->" +
                         rs.getString(4));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
