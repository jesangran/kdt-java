package avengers;

/** BattleStrategy 구현 — 지원 (10 / 40). */
public class SupportStrategy implements BattleStrategy {
    @Override
    public int damage() {
        return 10;
    }

    @Override
    public int teamScore() {
        return 40;
    }

    @Override
    public String label() {
        return "지원";
    }
}
