package App;

import Util.DatabaseConnection;
import model.Customer;
import modelview.CustomerDaoIml;
import repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM person";
        try {
             ps = conn.prepareCall(sql);
             rs = ps.executeQuery();
             while(rs.next()){
                 System.out.println(
                         rs.getString(1) + "-->" +
                         rs.getString(2) + "-->" +
                         rs.getString(3) + "-->" +
                         rs.getString(4)
                 );
             }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Customers:");
/*        sql = "Select person.first_name, customers.email From customers join person ON person.id = customers.person_id where person_id = ?";
        Object[] objects = new Object[]{1};
        List<Customer> customers = new CustomerDaoIml().query(sql,objects);
        System.out.println(customers);*/
        CustomerRepository customerRepository = new CustomerRepository();
        Map<Integer,Customer> customerMap = customerRepository.getAll();
        for(Map.Entry<Integer,Customer> entry : customerMap.entrySet()){
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }
        String info = "Kobe Brant 13";
        System.out.println(customerRepository.loginCheck(info));
    }
}
