package chapter02.lesson7;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * Map.compute — 키별 등장 횟수 올리기
 * ============================================================
 *
 * 핵심 한 줄
 *   count.compute(tag, (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
 *
 * 소리 내어 읽기
 *   "tag 키에 대해, 예전 값이 없었으면 1을 넣고, 있었으면 기존 값 + 1을 넣어라."
 *   = 등장 횟수 카운트
 *
 * ----- 1) compute의 큰 틀 -----
 *   count.compute( 키, 새값을_정하는_함수 )
 *   → "이 키의 값을, 함수가 돌려준 결과로 갱신해 줘"
 *
 *   키가 없으면 → oldValue 는 null
 *   키가 있으면 → oldValue 는 맵에 있던 숫자
 *
 * ----- 2) 람다 (key, oldValue) -> ... -----
 *   compute가 우리에게 두 값을 넘겨준다.
 *   key      : 지금 키 (보통 tag와 같음. 이 예제에선 안 써도 됨)
 *   oldValue : 기존 값 (없으면 null)
 *   -> 오른쪽이 "맵에 넣을 새 값"
 *
 * ----- 3) 삼항 연산자 A ? B : C -----
 *   oldValue == null ? 1 : oldValue + 1
 *   = if (oldValue == null) return 1; else return oldValue + 1;
 *
 * ----- 4) for문/if로 풀어 쓴 버전 (같은 의미) -----
 *   if (count.get(tag) == null) {
 *       count.put(tag, 1);
 *   } else {
 *       count.put(tag, count.get(tag) + 1);
 *   }
 *
 * ----- 5) "메모","퀴즈","메모" 따라가기 -----
 *   1) 메모 → old=null → 1     → 메모=1
 *   2) 퀴즈 → old=null → 1     → 메모=1, 퀴즈=1
 *   3) 메모 → old=1    → 1+1=2 → 메모=2, 퀴즈=1
 */
public class MapComputeDemo {
    public static void main(String[] args) {
        Map<String, Integer> count = new HashMap<>();

        for (String tag : new String[]{"메모", "퀴즈", "메모"}) {
            // compute(키, (키, 기존값) -> 새값)
            // 기존값이 null(처음)이면 1, 아니면 +1
            count.compute(tag, (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
            // key는 compute가 넘겨주는 자리(여기선 tag와 동일). 이 줄에서는 oldValue만 사용.

            System.out.println("메모=" + count.get("메모")); // 1 → 1 → 2
            System.out.println("퀴즈=" + count.get("퀴즈")); // null→1 후 유지
        }
    }
}
