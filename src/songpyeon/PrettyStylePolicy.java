package songpyeon;

/**
 * SongpyeonStylePolicy 구현체 — "예쁘게" 한 벌의 결과만 담는다.
 * 클래스 안에 if로 점수를 나누지 않는다. (전략 패턴의 핵심)
 */
public class PrettyStylePolicy implements SongpyeonStylePolicy {
    @Override
    public int pieceCount() {
        return 3;
    }

    @Override
    public int shapeScore() {
        return 90;
    }

    @Override
    public String label() {
        return "예쁘게";
    }
}
