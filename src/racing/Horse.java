package racing;

/**
 * [Step 1] 말(Horse)
 *
 * 요구사항:
 * - id, name 필드
 * - 둘 다 private final (바깥에서 직접 못 바꾸고, 생성 후 값 고정)
 * - getter만 제공 (출력/문장 조립은 호출하는 쪽에서 getName() 등으로 함)
 * - toString() 만들지 않음 → 화면 출력과 도메인 로직을 섞지 않기 위함
 */
public class Horse {
    private final int id;       // 말 번호
    private final String name;  // 말 이름

    public Horse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
