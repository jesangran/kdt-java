package chapter02.lesson7;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * ============================================================
 * 함수형 인터페이스 4형제 (람다의 "상자")
 * ============================================================
 *
 * ★ 람다만 먼저 이해하기 (여기서는 Stream 아직 없음)
 *
 * 람다 = 이름 없는 짧은 함수
 *   post -> post.isClosed()
 *   읽는 법: "post를 받아서 → isClosed() 결과를 돌려줘"
 *
 * 자바는 타입을 밝히길 원해서, 함수를 담을 "상자"를 미리 만들어 두었다.
 * 그게 함수형 인터페이스다.
 *
 * ┌──────────────┬────────────────────┬────────────┬──────────────┐
 * │ 타입         │ 하는 일            │ 호출 메서드│ 비유         │
 * ├──────────────┼────────────────────┼────────────┼──────────────┤
 * │ Predicate<T> │ 조건 → true/false  │ test()     │ 체온계       │
 * │ Function<T,R>│ A를 B로 변환       │ apply()    │ 번역기       │
 * │ Consumer<T>  │ 받아서 쓰기(반환X) │ accept()   │ 프린터       │
 * │ Supplier<T>  │ 값만 만들어 줌     │ get()      │ 자판기       │
 * └──────────────┴────────────────────┴────────────┴──────────────┘
 *
 * ★ 나중에 Stream과 이렇게 연결된다
 *   filter(조건)  → Predicate 를 받음
 *   map(변환)     → Function 을 받음
 *   forEach(출력) → Consumer 를 받음
 * 그래서 "Stream 안에 람다가 자주 보이는" 것이다.
 * Stream = 벨트, 람다 = 벨트에 붙이는 작업 설명서.
 */
public class FuncTypes {

    public static void main(String[] args) {
        // 람다 문법: (매개변수) -> 결과
        Predicate<String> isExam = text -> text.equals("시험");           // 조건
        Function<String, Integer> length = text -> text.length();         // 변환
        Consumer<String> print = text -> System.out.println(text);        // 소비(출력)
        Supplier<String> fallback = () -> "없음";                         // 공급(입력 없음)

        System.out.println("조건 =" + isExam.test("시험"));   // Predicate는 test()
        System.out.println("길이 =" + length.apply("Java")); // Function은 apply()
        print.accept("출력");                                 // Consumer는 accept()
        System.out.println("공급= " + fallback.get());        // Supplier는 get()
    }
}
