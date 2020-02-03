package repository;

import model.Customer;
import modelview.CustomerDaoIml;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerRepository implements IRepository{
    private CustomerDaoIml customerDaoIml = new CustomerDaoIml();

    @Override
    public Map<Integer,Customer> getAll() {
        String sql = "SELECT person.id, first_name,last_name,security_id,password,email FROM person "+
                     "JOIN customers ON person.id = customers.person_id;";
        List<Customer> customers =customerDaoIml.query(sql,new Object[]{});
        return customers.stream().collect(Collectors.toMap(Customer::getId,customer->customer));
    }

    public boolean loginCheck(String loginInfo){
        Map<Integer,Customer> allCustomers = getAll();
        boolean succeedLogin = false;
        for(Customer customer : allCustomers.values()){
            if ((customer.getFirstName() + " " + customer.getLastName() + " " + customer.getPassword())
                    .equals(loginInfo)){
                succeedLogin = true;
                break;
            }
        }
        return succeedLogin;
    }
}
