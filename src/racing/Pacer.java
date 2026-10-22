package racing;

/**
 * [전략 구현] 균속 — 24/26/28
 * execute 순서는 부모 템플릿, 여기는 run만 구현
 */
public class Pacer extends AbstractRaceStrategy {
    public Pacer() {
        super("균속");
    }

    @Override
    protected int run(int round) {
        if (round == 1) {
            return 24;
        } else if (round == 2) {
            return 26;
        } else if (round == 3) {
            return 28;
        } else {
            return 0;
        }
    }
}
