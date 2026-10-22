package avengers;

import java.util.ArrayList;

/**
 * ============================================================
 * [Service] 미션 등록·배정 — "필드 수정"이 아니라 "새 객체로 교체"
 * ============================================================
 * Mission 필드는 private final 이라 배정 시 heroName을 바꿀 수 없다.
 * → 같은 id/title + 새 heroName 으로 Mission을 새로 만들고 replace.
 *
 * HeroRepository는 모른다. Controller가 히어로를 찾아 이름만 assign에 넘긴다.
 * (songpyeon에서 가족 이름만 넘기던 것과 동일)
 */
public class MissionService {
    private final MissionRepository repository;

    public MissionService(MissionRepository repository) {
        this.repository = repository;
    }

    public Mission register(String title) {
        if (title == null || title.equals("")) {
            throw new IllegalArgumentException("미션 이름이 비어 있습니다.");
        }
        int id = repository.nextId();
        Mission mission = new Mission(id, title, ""); // 배정 전 heroName은 빈 문자열
        repository.save(mission);
        return mission;
    }

    public Mission assign(int id, String heroName) {
        if (heroName == null || heroName.equals("")) {
            throw new IllegalArgumentException("배정할 히어로 이름이 없습니다.");
        }
        Mission old = repository.findById(id);
        // 기존 객체의 필드를 고치지 않고 새 Mission 생성
        Mission updated = new Mission(old.getId(), old.getTitle(), heroName);
        repository.replace(updated); // save가 아님 → ids에 번호 다시 add 하지 않음
        return updated;
    }

    public Mission findById(int id) {
        return repository.findById(id);
    }

    public ArrayList<Mission> findAll() {
        return repository.findAll();
    }
}
