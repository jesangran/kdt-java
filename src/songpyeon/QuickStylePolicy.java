package songpyeon;

/** SongpyeonStylePolicy 구현체 — "빠르게" (10개 / 40점). Pretty와 쌍을 이룬다. */
public class QuickStylePolicy implements SongpyeonStylePolicy {
    @Override
    public int pieceCount() {
        return 10;
    }

    @Override
    public int shapeScore() {
        return 40;
    }

    @Override
    public String label() {
        return "빠르게";
    }
}
