package racing;

/**
 * [인터페이스] 상금 계산 — 주행 전략(RaceStrategy)과 책임을 분리.
 * 라운드 점수를 바꾸는 이유와 상금표를 바꾸는 이유는 다르므로 인터페이스를 나눈다.
 */
public interface PrizeCalculator {
    int prizeOf(int rank);
}
