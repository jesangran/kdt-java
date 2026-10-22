package songpyeon;

/**
 * ============================================================
 * [전략 패턴] 빚기 스타일의 "약속(인터페이스)"
 * ============================================================
 * lesson7의 Predicate처럼 "역할을 타입으로 정하고, 구현은 여러 개"인 구조다.
 *
 *   SongpyeonStylePolicy policy = new PrettyStylePolicy(); // 또는 QuickStylePolicy
 *   policy.pieceCount();  // 호출 코드는 같고, 결과는 구현 클래스마다 다름 → 다형성
 *
 * 왜 if (스타일==1) 점수=90 else ... 를 서비스에 안 쓰나?
 *   → 스타일이 늘수록 if가 커진다. 클래스 하나로 "결과 한 벌"을 분리한다.
 *
 * 구현체: PrettyStylePolicy(예쁘게 3개/90점), QuickStylePolicy(빠르게 10개/40점)
 */
public interface SongpyeonStylePolicy {
    /** 빚은 개수 → Songpyeon.count 에 들어감 */
    int pieceCount();

    /** 모양 점수 */
    int shapeScore();

    /** 화면에 찍을 이름 ("예쁘게", "빠르게") */
    String label();
}
