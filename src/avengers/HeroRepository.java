package avengers;

import java.util.ArrayList;
import java.util.HashMap;

/** [Repository] HashMap(조회) + ArrayList ids(등록 순서). songpyeon과 동일 패턴. */
public class HeroRepository {
    private final HashMap<Integer, Hero> store = new HashMap<>();
    private final ArrayList<Integer> ids = new ArrayList<>();
    private int nextNumber = 1;

    public int nextId() {
        int id = nextNumber;
        nextNumber = nextNumber + 1;
        return id;
    }

    public void save(Hero hero) {
        store.put(hero.getId(), hero);
        ids.add(hero.getId());
    }

    public Hero findById(int id) {
        Hero hero = store.get(id);
        if (hero == null) {
            throw new HeroNotFoundException("히어로를 찾을 수 없습니다. 번호=" + id);
        }
        return hero;
    }

    public ArrayList<Hero> findAll() {
        ArrayList<Hero> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(store.get(ids.get(i)));
        }
        return result;
    }
}
