package racing;

/**
 * [전략 구현] 선입 — 매 라운드 30
 * execute 순서는 부모 템플릿, 여기는 run만 구현
 */
public class Marker extends AbstractRaceStrategy {
    public Marker() {
        super("선입");
    }

    @Override
    protected int run(int round) {
        if (round == 1) {
            return 30;
        } else if (round == 2) {
            return 30;
        } else if (round == 3) {
            return 30;
        } else {
            return 0;
        }
    }
}
