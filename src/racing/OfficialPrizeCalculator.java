package racing;

/** PrizeCalculator 구현 — 1위 100, 2위 40, 3위 20, 그 외 0. */
public class OfficialPrizeCalculator implements PrizeCalculator {
    @Override
    public int prizeOf(int rank) {
        if (rank == 1) {
            return 100;
        } else if (rank == 2) {
            return 40;
        } else if (rank == 3) {
            return 20;
        } else {
            return 0;
        }
    }
}
