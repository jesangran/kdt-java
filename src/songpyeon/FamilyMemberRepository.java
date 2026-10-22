package songpyeon;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ============================================================
 * [Repository] HashMap + ArrayList 역할 분리
 * ============================================================
 * HashMap<Integer, FamilyMember> store
 *   → 번호로 한 건 빠르게 찾기 (findById)
 * ArrayList<Integer> ids
 *   → 등록 순서 기억 (HashMap만 쓰면 순서가 보장되지 않음)
 *
 * findById는 null 반환 금지 → 없으면 커스텀 예외.
 * findAll은 store.values()를 그대로 주지 않고, ids 순서로 새 리스트를 만든다.
 */
public class FamilyMemberRepository {
    private final HashMap<Integer, FamilyMember> store = new HashMap<>();
    private final ArrayList<Integer> ids = new ArrayList<>();
    private int nextNumber = 1;

    public int nextId() {
        int id = nextNumber;
        nextNumber = nextNumber + 1;
        return id;
    }

    public void save(FamilyMember member) {
        store.put(member.getId(), member);
        ids.add(member.getId()); // 순서 목록에도 번호 기록
    }

    public FamilyMember findById(int id) {
        FamilyMember member = store.get(id);
        if (member == null) {
            throw new SongpyeonNotFoundException("가족을 찾을 수 없습니다. 번호=" + id);
        }
        return member;
    }

    public ArrayList<FamilyMember> findAll() {
        ArrayList<FamilyMember> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(store.get(ids.get(i))); // 등록 순서대로
        }
        return result;
    }
}
