package songpyeon;

import java.util.ArrayList;

/**
 * ============================================================
 * [Service] 송편 등록 규칙 + 전략 선택
 * ============================================================
 * Repository는 "저장/조회"만, Service는 "검증·계산·조합"을 담당한다.
 *
 * 생성자 주입
 *   new SongpyeonService(repository)  ← 저장소를 밖에서 받아 필드로 보관
 *   Service 안에서 new SongpyeonRepository() 하지 않는다. (조립은 Controller.main)
 *
 * 다형성 사용 지점
 *   SongpyeonStylePolicy policy = new PrettyStylePolicy(); // 또는 Quick
 *   policy.pieceCount() / shapeScore() / label()  // 호출은 동일
 *
 * 주의: 이 서비스는 가족 저장소를 모른다.
 *   가족 이름은 Controller가 FamilyMemberService에서 조회해 문자열로 넘긴다.
 */
public class SongpyeonService {
    private final SongpyeonRepository repository;

    public SongpyeonService(SongpyeonRepository repository) {
        this.repository = repository;
    }

    public Songpyeon register(String name, String makerName, int styleChoice) {
        if (name == null || name.equals("") || makerName == null || makerName.equals("")) {
            throw new IllegalArgumentException("송편 이름과 빚은 사람 이름이 필요합니다.");
        }

        // 전략 선택: if로 구현 객체만 고르고, 점수 계산 if는 쓰지 않음
        SongpyeonStylePolicy policy;
        if (styleChoice == 1) {
            policy = new PrettyStylePolicy();
        } else if (styleChoice == 2) {
            policy = new QuickStylePolicy();
        } else {
            throw new IllegalArgumentException("스타일은 1 또는 2입니다.");
        }

        int id = repository.nextId();
        Songpyeon songpyeon = new Songpyeon(
                id,
                name,
                makerName,
                policy.label(),
                policy.pieceCount(),
                policy.shapeScore()
        );
        repository.save(songpyeon);
        return songpyeon;
    }

    public Songpyeon findById(int id) {
        return repository.findById(id);
    }

    public ArrayList<Songpyeon> findAll() {
        return repository.findAll();
    }
}
