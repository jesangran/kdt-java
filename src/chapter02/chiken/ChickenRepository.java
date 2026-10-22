package chapter02.chiken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChickenRepository {

    private final Map<Integer, Chicken> store = new HashMap<>(); //key value
    //Chicken id > int > 고유한 값이라서 Integer

    public void save(Chicken chicken) {
        store.put(chicken.getId(), chicken);
    }

    public Chicken findById(int id) {
        Chicken found = store.get(id);
        if (found == null) {
            throw new ChickenNotFoundException(id);
        }
        return found;
    }

    public List<Chicken> findAll() { //치킨list 반환
        return new ArrayList<>(store.values());
    }
}
