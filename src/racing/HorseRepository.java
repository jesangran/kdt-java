package racing;

import java.util.HashMap;

/**
 * [Repository] save(String)이 객체 생성까지 담당.
 * 경주 없음도 HorseNotFoundException + 메시지만 "경주를..." (예외 3개 제한).
 */
public class HorseRepository {
    private final HashMap<Integer, Horse> store = new HashMap<>();
    private int nextNumber = 1;

    public int nextId() {
        int id = nextNumber;
        nextNumber = nextNumber + 1;
        return id;
    }

    public Horse save(String name) {
        int id = nextId();
        Horse horse = new Horse(id, name);
        store.put(id, horse);
        return horse;
    }

    public Horse findById(int id) {
        Horse horse = store.get(id);
        if (horse == null) {
            throw new HorseNotFoundException("말을 찾을 수 없습니다. 번호=" + id);
        }
        return horse;
    }
}
