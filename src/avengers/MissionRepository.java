package avengers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * [Repository] save는 put+ids.add, replace는 put만 (등록 순서 ids는 그대로).
 * 배정으로 객체를 바꿔도 미션 목록에 1번이 두 번 나오면 안 된다.
 */
public class MissionRepository {
    private final HashMap<Integer, Mission> store = new HashMap<>();
    private final ArrayList<Integer> ids = new ArrayList<>();
    private int nextNumber = 1;

    public int nextId() {
        int id = nextNumber;
        nextNumber = nextNumber + 1;
        return id;
    }

    public void save(Mission mission) {
        store.put(mission.getId(), mission);
        ids.add(mission.getId());
    }

    /** 이미 있는 번호의 객체만 교체. ids.add 금지 */
    public void replace(Mission mission) {
        findById(mission.getId()); // 없으면 예외
        store.put(mission.getId(), mission);
    }

    public Mission findById(int id) {
        Mission mission = store.get(id);
        if (mission == null) {
            throw new HeroNotFoundException("미션을 찾을 수 없습니다. 번호=" + id);
        }
        return mission;
    }

    public ArrayList<Mission> findAll() {
        ArrayList<Mission> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(store.get(ids.get(i)));
        }
        return result;
    }
}
