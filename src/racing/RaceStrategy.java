package racing;

/**
 * [전략 패턴] 주행 방식을 갈아끼우기 위한 역할 약속
 *
 * execute(round) : 그 라운드 점수
 * label()        : "선행","추입" 등 화면 이름
 *
 * 실제 구현은 AbstractRaceStrategy(템플릿 메서드)를 거쳐
 * FrontRunner / Closer / Marker / Pacer 로 이어짐
 * → 전략 패턴(교체) + 템플릿 메서드(순서 고정)가 함께 쓰임
 */
public interface RaceStrategy {
    int execute(int round);

    String label();
}
