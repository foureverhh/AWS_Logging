package modelview;

import java.sql.Connection;
import java.util.List;

/* Dao crud*/
public interface Dao <T>{
   List<T> query(String sql,Object[] objects);
   void save(T t);
   void update(T t);
   void delete(T t);
}
