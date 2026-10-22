package avengers;

/** BattleStrategy 구현 — 근접 (피해 40 / 팀 15). 클래스 하나에 결과 한 벌. */
public class MeleeStrategy implements BattleStrategy {
    @Override
    public int damage() {
        return 40;
    }

    @Override
    public int teamScore() {
        return 15;
    }

    @Override
    public String label() {
        return "근접";
    }
}
