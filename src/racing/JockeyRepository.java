package racing;

import java.util.HashMap;

/** [Repository] 기수. HorseRepository와 동일 패턴. */
public class JockeyRepository {
    private final HashMap<Integer, Jockey> store = new HashMap<>();
    private int nextNumber = 1;

    public int nextId() {
        int id = nextNumber;
        nextNumber = nextNumber + 1;
        return id;
    }

    public Jockey save(String name) {
        int id = nextId();
        Jockey jockey = new Jockey(id, name);
        store.put(id, jockey);
        return jockey;
    }

    public Jockey findById(int id) {
        Jockey jockey = store.get(id);
        if (jockey == null) {
            throw new JockeyNotFoundException("기수를 찾을 수 없습니다. 번호=" + id);
        }
        return jockey;
    }
}
