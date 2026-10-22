package avengers;

/** BattleStrategy 구현 — 원거리 (25 / 25). */
public class RangedStrategy implements BattleStrategy {
    @Override
    public int damage() {
        return 25;
    }

    @Override
    public int teamScore() {
        return 25;
    }

    @Override
    public String label() {
        return "원거리";
    }
}
