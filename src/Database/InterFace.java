package Database;

import java.util.ArrayList;

public interface InterFace {
    void add() throws Exception;
    void update(Object oldObj) throws Exception;
    void delete(Object oldObj) throws Exception;
    Object select(String id) throws Exception;
    ArrayList<String> toArray();
    ArrayList<?> getAll() throws Exception;

}
