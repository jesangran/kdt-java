package racing;

/**
 * [Step 1] 기수(Jockey)
 *
 * 요구사항: Horse와 같은 구조
 * - id, name → private final
 * - getter만 제공
 * - toString() 없음 (출력은 호출하는 쪽에서 getter로 문장 조립)
 */
public class Jockey {
    private final int id;       // 기수 번호
    private final String name;  // 기수 이름

    public Jockey(int id, String name) {
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
