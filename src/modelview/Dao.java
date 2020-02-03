package modelview;

import java.sql.Connection;
import java.util.List;

/* Dao crud*/
public interface Dao <T>{
   List<T> query(String sql,Object[] objects);
   void add(String sql, T t);
   void update(String sql,T t);
   void delete(String sql,T t);
}
