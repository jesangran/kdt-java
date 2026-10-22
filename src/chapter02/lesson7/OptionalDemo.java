package chapter02.lesson7;

import chapter02.chiken.ChickenNotFoundException;

import java.util.Optional;

/**
 * ============================================================
 * Optional — null 대신 "있을 수도 / 없을 수도"를 표현하는 상자
 * ============================================================
 *
 * ★ 왜 배우나? (문제)
 *   String name = null;
 *   System.out.println(name.length()); // NullPointerException (NPE)
 *
 *   null을 깜빡하고 쓰면 프로그램이 죽는다.
 *   Optional은 "이 값은 없을 수 있어"를 타입으로 표시해서,
 *   꺼내기 전에 orElse / ifPresent / orElseThrow 중 하나를 고르게 만든다.
 *
 * ★ 비유
 *   Optional = 선물 상자
 *   - of("김밥")  → 상자 안에 김밥이 들어 있음
 *   - empty()     → 상자만 있고 비어 있음
 *   뚜껑을 열 때(꺼낼 때) "없으면 품절", "없으면 예외"처럼 대처를 정한다.
 *
 * ★ 만드는 법
 *   Optional.of(값)      값 있는 상자 (값에 null을 넣으면 안 됨 → NPE)
 *   Optional.ofNullable(값)  값이 null이면 empty, 아니면 of (실무에서 자주)
 *   Optional.empty()     빈 상자
 *
 * ★ 자주 쓰는 "꺼내기" 4종
 *
 * 1) orElse(기본값)
 *    없으면 이 기본값을 쓴다.
 *    hit.orElse("품절")  → "김밥"
 *    empty.orElse("품절") → "품절"
 *
 * 2) orElseGet(() -> 기본값)  ← Supplier 람다
 *    없을 때만 기본값을 "계산"한다.
 *    기본값을 만드는 비용이 클 때 orElse보다 유리하다.
 *    (orElse는 인자를 먼저 만들어 두고, orElseGet은 필요할 때만 실행)
 *
 * 3) ifPresent(값 -> 할일)  ← Consumer 람다
 *    있을 때만 실행. 없으면 조용히 넘어감.
 *    if (값있음) { 출력 } 과 비슷.
 *
 * 4) orElseThrow(() -> 예외)  ← Supplier 람다
 *    없으면 예외를 던진다. "반드시 있어야 하는 값"에 사용.
 *
 * ★ Stream과 연결 (중요!)
 *   StreamSearch의 findFirst()가 Optional을 돌려주는 이유:
 *   "저녁 글이 있을 수도, 없을 수도" 이기 때문.
 *
 *   posts.stream()
 *       .filter(...)
 *       .map(...)
 *       .findFirst()          // Optional<String>
 *       .orElse("저녁 모임이 없다.");
 *
 * ★ 실무 팁
 *   Optional은 주로 "메서드 반환값"에 쓴다.
 *   필드나 매개변수에 Optional을 잔뜩 넣는 스타일은 보통 비추천.
 *
 * ★ if문으로 비슷한 느낌 (Optional 없이)
 *   String hit = "김밥";
 *   if (hit != null) {
 *       System.out.println("선택=" + hit);
 *   } else {
 *       System.out.println("품절");
 *   }
 *   Optional은 null 체크를 메서드 이름으로 읽히게 만든 버전이라고 생각하면 된다.
 */
public class OptionalDemo {
    public static void main(String[] args) {

        // ---------- 상자 만들기 ----------
        // hit  : 안에 "김밥"이 들어 있는 Optional
        // empty: 비어 있는 Optional
        Optional<String> hit = Optional.of("김밥");
        Optional<String> empty = Optional.empty();

        // ---------- orElse ----------
        // "없으면 품절". hit에는 값이 있어서 결과는 "김밥"
        // (반환값을 변수에 안 담고 호출만 한 수업용 줄)
        hit.orElse("품절");

        // ---------- orElseGet (Supplier) ----------
        // () -> "품절" 은 "필요할 때 품절 문자열을 만들어 줘"라는 Supplier
        // hit  : 값이 있으므로 Supplier는 실행 안 되고 "김밥"
        // empty: 비어 있으므로 Supplier 실행 → "품절"
        System.out.println("hit=" + hit.orElseGet(() -> "품절"));
        System.out.println("miss=" + empty.orElseGet(() -> "품절"));

        // ---------- ifPresent (Consumer) ----------
        // 값이 있을 때만 람다 실행. empty.ifPresent(...)면 아무것도 안 함
        hit.ifPresent(value -> System.out.println("선택=" + value));
        // empty.ifPresent(value -> System.out.println("선택=" + value)); // 출력 없음

        // ---------- orElseThrow (Supplier) ----------
        // 값이 없으면 예외를 만들어 던진다.
        // hit에는 "김밥"이 있으므로 예외 없이 통과 (반환값은 "김밥"이지만 여기서는 안 씀)
        // 아래를 empty.orElseThrow(...) 로 바꾸면 ChickenNotFoundException 발생
        hit.orElseThrow(
                () -> new ChickenNotFoundException(1) // 없을 때만 이 예외 생성
        );
        // 참고: ChickenNotFoundException은 chapter02.chiken 패키지 수업용 예외.
        //       Optional 연습에서 "없으면 예외"를 보여 주려고 빌려 쓴 것.
    }
}
