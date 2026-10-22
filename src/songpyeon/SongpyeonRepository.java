package songpyeon;

import java.util.ArrayList;
import java.util.HashMap;

/** [Repository] 송편용. FamilyMemberRepository와 구조 동일 (값 타입만 Songpyeon). */
public class SongpyeonRepository {
    private final HashMap<Integer, Songpyeon> store = new HashMap<>();
    private final ArrayList<Integer> ids = new ArrayList<>();
    private int nextNumber = 1;

    public int nextId() {
        int id = nextNumber;
        nextNumber = nextNumber + 1;
        return id;
    }

    public void save(Songpyeon songpyeon) {
        store.put(songpyeon.getId(), songpyeon);
        ids.add(songpyeon.getId());
    }

    public Songpyeon findById(int id) {
        Songpyeon songpyeon = store.get(id);
        if (songpyeon == null) {
            throw new SongpyeonNotFoundException("송편을 찾을 수 없습니다. 번호=" + id);
        }
        return songpyeon;
    }

    public ArrayList<Songpyeon> findAll() {
        ArrayList<Songpyeon> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(store.get(ids.get(i)));
        }
        return result;
    }
}
