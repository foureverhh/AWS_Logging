package modelview;

import Util.DatabaseConnection;
import model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoIml implements Dao {
    @Override
    public List<Customer> query(String sql,Object[] objects) {
        List<Customer> customerList =  new ArrayList<>();
        ResultSet rs = DatabaseConnection.getInstance().executesSQL(sql,objects);
        try {
            while (rs.next()) {
                //int id, String firstName, String lastName, String security, String password, String email) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String security = rs.getString(4);
                String password = rs.getString(5);
                String email = rs.getString(6);
                customerList.add(new Customer(id,firstName,lastName,security,password,email));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public void add(String sql, Object o) {
        Customer customer = (Customer) o;

        //DatabaseConnection.getInstance().executesSQL(sql,);
    }

    @Override
    public void update(String sql,Object o) {

    }

    @Override
    public void delete(String sql,Object o) {

    }
}
