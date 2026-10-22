package racing;

/**
 * [템플릿 메서드 패턴] + [전략 패턴의 공통 부모]
 *
 * 템플릿 메서드란?
 * - "실행 순서(뼈대)는 부모가 고정"하고
 * - "달라지는 단계만 자식이 구현"하는 패턴
 *
 * 이 클래스의 뼈대 (execute가 템플릿 메서드):
 *   prepare() → run(round) → finish(pace)
 *
 * why final?
 * - 자식(FrontRunner 등)이 execute 순서를 통째로 바꾸지 못하게 잠금
 *
 * why abstract run?
 * - 선행/추입/선입/균속마다 점수표만 다르므로 자식이 반드시 구현
 *
 * 동적 바인딩 예시:
 *   RaceStrategy s = new Closer();
 *   s.execute(3);
 *   → execute 몸통은 여기(부모), 안의 run()은 Closer의 run (42점)
 *
 * 인터페이스 default로는 final로 순서를 못 잠근다 → 추상 클래스가 필요한 이유
 */
public abstract class AbstractRaceStrategy implements RaceStrategy {
    private final String label; // 화면용 이름 ("선행", "추입" ...)

    protected AbstractRaceStrategy(String label) {
        this.label = label;
    }

    /**
     * [템플릿 메서드] 실행 순서를 고정한 메서드
     * 호출하는 쪽은 execute만 부르면 되고, 안쪽 단계는 부모가 배치함
     */
    @Override
    public final int execute(int round) {
        prepare();                 // 1) 공통 전처리
        int pace = run(round);     // 2) 전략별 점수 (자식 구현이 호출됨)
        return finish(pace);       // 3) 공통 후처리 후 점수 반환
    }

    @Override
    public String label() {
        return label;
    }

    /** 훅(공통 전처리). 지금은 비어 있음 — 나중에 공통 로직 넣을 자리 */
    protected void prepare() {
    }

    /**
     * 훅(필수 구현) — 전략마다 다른 점수 계산
     * FrontRunner/Closer/Marker/Pacer가 여기만 @Override
     */
    protected abstract int run(int round);

    /** 훅(공통 후처리). 이번 과제는 점수를 그대로 반환 */
    protected int finish(int pace) {
        return pace;
    }
}
