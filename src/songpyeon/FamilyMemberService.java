package songpyeon;

import java.util.ArrayList;

/**
 * [Service] 가족 등록·조회. 저장소는 생성자로만 받는다(생성자 주입).
 * 빈 이름 검사는 여기, HashMap 조작은 Repository — 계층 분리.
 */
public class FamilyMemberService {
    private final FamilyMemberRepository repository;

    public FamilyMemberService(FamilyMemberRepository repository) {
        this.repository = repository;
    }

    public FamilyMember register(String name) {
        // null 검사를 equals보다 먼저 (NPE 방지)
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("가족 이름이 비어 있습니다.");
        }
        int id = repository.nextId();
        FamilyMember member = new FamilyMember(id, name);
        repository.save(member);
        return member;
    }

    public FamilyMember findById(int id) {
        return repository.findById(id);
    }

    public ArrayList<FamilyMember> findAll() {
        return repository.findAll();
    }
}
