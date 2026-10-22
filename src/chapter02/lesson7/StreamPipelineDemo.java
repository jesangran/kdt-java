package chapter02.lesson7;

import java.util.List;

/**
 * ============================================================
 * Stream 파이프라인 — 중간 연산을 여러 개 연결
 * ============================================================
 *
 * ★ 파이프라인이란?
 *   수도관처럼 공정을 이어 붙인 것.
 *   위 → 아래가 처리 순서다.
 *
 *   원본 → filter → map → distinct → sorted → limit → toList
 *          (중간들…)                              (최종)
 *
 * ★ 중간 연산 vs 최종 연산 (다시)
 *   중간: filter, map, distinct, sorted, limit …
 *        → "이렇게 해줘" 예약. 아직 결과가 안 나옴.
 *   최종: toList, forEach, count, anyMatch, allMatch, findFirst …
 *        → 여기서 실제로 돌아가며 결과 생성.
 *
 * ★ 지연 평가 (lazy)
 *   최종 연산을 만나기 전까지는 거의 일을 안 한다.
 *   filter만 적고 끝나면 → 아무 일도 안 한 것처럼 보일 수 있다.
 *
 * ============================================================
 * 데이터: ["closed", "exam", "closed", "quiz"]
 * ============================================================
 *   filter (c 또는 e로 시작) → closed, exam, closed
 *   map 대문자               → CLOSED, EXAM, CLOSED
 *   distinct                 → CLOSED, EXAM
 *   sorted                   → CLOSED, EXAM  (사전순, 이미 정렬됨)
 *   limit(2)                 → CLOSED, EXAM  (앞에서 2개)
 *   toList                   → List ["CLOSED", "EXAM"]
 *
 * ★ allMatch — "전부 조건 만족?"
 *   anyMatch : 하나라도?   (StreamSearch)
 *   allMatch : 전부다?
 *   noneMatch: 하나도 아님?
 *
 * ★ 메서드 참조
 *   String::toUpperCase  ==  s -> s.toUpperCase()
 *
 * ★ for문 느낌 (완벽히 같진 않지만 의미만)
 *   조건에 맞는 것만 골라 → 대문자 → 중복 제거 → 정렬 → 앞에서 2개만 리스트.
 */
public class StreamPipelineDemo {
    public static void main(String[] args) {
        List<String> titles = List.of("closed", "exam", "closed", "quiz");

        // 파이프라인: 각 줄이 한 공정. .으로 이어짐 (메서드 체이닝)
        List<String> top = titles.stream()
                .filter(t -> t.startsWith("c") || t.startsWith("e")) // 1) 조건
                .map(String::toUpperCase) // 2) 대문자 변환  CLOSED, EXAM, CLOSED
                .distinct()               // 3) 중복 제거    CLOSED, EXAM
                .sorted()                 // 4) 정렬         CLOSED, EXAM
                .limit(2)                 // 5) 앞에서 2개만
                .toList();                // 6) 최종: List로 수집

        // "모든" 원소가 길이 6 이하인가? → true/false
        boolean allShort = titles.stream()
                .allMatch(t -> t.length() <= 6); // quiz, closed, exam 모두 <=6 → true

        System.out.println("top=" + top);         // [CLOSED, EXAM]
        System.out.println("allShort=" + allShort);
    }
}
