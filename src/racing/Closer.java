package racing;

/**
 * [전략 구현] 추입 — 16/28/42
 * FrontRunner와 구조 동일, run 점수표만 다름 (템플릿의 훅만 채움)
 */
public class Closer extends AbstractRaceStrategy {
    public Closer() {
        super("추입");
    }

    @Override
    protected int run(int round) {
        if (round == 1) {
            return 16;
        } else if (round == 2) {
            return 28;
        } else if (round == 3) {
            return 42;
        } else {
            return 0;
        }
    }
}
