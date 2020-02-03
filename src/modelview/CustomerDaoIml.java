package modelview;

import Util.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class CustomerDaoIml implements Dao {
    @Override
    public List query(String sql,Object[] objects) {
        return DatabaseConnection.getInstance().query(sql,objects);
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(Object o) {

    }
}
