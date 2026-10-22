package racing;

/**
 * [전략 구현] 선행 — 라운드 1/2/3 → 40/28/16
 *
 * execute는 부모(AbstractRaceStrategy)의 final 템플릿이 담당
 * 여기서는 달라지는 부분(run 점수표)만 @Override
 */
public class FrontRunner extends AbstractRaceStrategy {
    public FrontRunner() {
        super("선행");
    }

    @Override
    protected int run(int round) {
        if (round == 1) {
            return 40;
        } else if (round == 2) {
            return 28;
        } else if (round == 3) {
            return 16;
        } else {
            return 0; // 1~3이 아니면 0
        }
    }
}
