package racing;

/**
 * [Service] 전략 선택만 담당 (저장소 없음)
 *
 * choose(1~4) → FrontRunner/Closer/Marker/Pacer
 * 반환 타입은 RaceStrategy (인터페이스) → 호출부는 구체 클래스를 몰라도 됨
 *
 * 호출 예: strategy.execute(round)
 * → AbstractRaceStrategy의 템플릿(execute)이 돌고, 안의 run은 선택된 전략 구현체
 */
public class StrategyService {
    public RaceStrategy choose(int choice) {
        if (choice == 1) {
            return new FrontRunner();
        } else if (choice == 2) {
            return new Closer();
        } else if (choice == 3) {
            return new Marker();
        } else if (choice == 4) {
            return new Pacer();
        } else {
            throw new InvalidStrategyException("전략은 1, 2, 3, 4입니다.");
        }
    }
}
