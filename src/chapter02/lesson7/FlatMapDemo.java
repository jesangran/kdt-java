package chapter02.lesson7;

import java.util.List;

/**
 * ============================================================
 * flatMap — 겹친 리스트를 펼치기
 * ============================================================
 *
 * map vs flatMap (초급이 가장 헷갈리는 지점)
 *
 * map
 *   원소 하나를 "다른 값"으로 바꾼다.
 *   원소가 이미 List라면 → List<List<...>> 구조가 그대로 남는다.
 *
 * flatMap
 *   원소를 Stream으로 바꾼 뒤, 그 Stream들을 "한 줄로 이어 붙인다".
 *   즉, 한 겹의 괄호([])를 벗겨 평평하게 만든다.
 *
 * ----- "1차원 / 2차원 / 3차원 리스트"가 무슨 말인가? -----
 * 리스트가 몇 겹으로 싸여 있는지를 세는 표현이다.
 * (수학 배열 arr[i][j][k] 처럼, 값에 닿으려면 get을 몇 번 해야 하나?)
 *
 *   1차원  [a, b, c]
 *     → 값만 한 줄. get 한 번이면 문자열.
 *       list.get(0) → "a"
 *
 *   2차원  [[a, b], [c, d]]
 *     → "행"마다 또 리스트. get 두 번.
 *       tagRows.get(0).get(1) → "stream"
 *       타입: List<List<String>>
 *
 *   3차원  [[[a, b], [c, d]], [[e, f], ...]]
 *     → "묶음" 안에 또 "행"들이 있음. get 세 번.
 *       tagCubes.get(0).get(1).get(0) → "stream"
 *       타입: List<List<List<String>>>
 *
 * flatMap은 "한 겹의 []"만 벗긴다.
 *   2차원 → flatMap 1번 → 1차원
 *   3차원 → flatMap 2번 → 1차원
 *
 * 그림 (2차원)
 *   [[java, stream], [stream, lambda]]
 *        -- flatMap 1번 -->
 *   [java, stream, stream, lambda]
 *
 * 그림 (3차원)
 *   [[[java,stream],[stream,lambda]], [[lambda,optional],[java,record]]]
 *        -- flatMap 1번 -->  2차원
 *   [[java,stream],[stream,lambda], [lambda,optional],[java,record]]
 *        -- flatMap 2번 -->  1차원
 *   [java, stream, stream, lambda, lambda, optional, java, record]
 *
 * 실무 비유
 *   게시글마다 태그 목록(2차원에 가까움)이 있을 때
 *   "모든 태그를 한 리스트로" 만들 때 flatMap.
 *   수업의 3차원은 "겹이 많으면 flatMap을 더 쓴다"를 보여 주는 예제이지,
 *   실무에서 3차원 리스트를 자주 쓰라는 뜻은 아니다.
 *
 * List::stream  =  list -> list.stream()
 */
public class FlatMapDemo {
    public static void main(String[] args) {
        // =========================================================
        // [2차원] List<List<String>>
        // 행0: java, stream
        // 행1: stream, lambda
        // get 두 번 → 문자열  (예: tagRows.get(0).get(1) == "stream")
        // =========================================================
        List<List<String>> tagRows = List.of(
                List.of("java", "stream"),
                List.of("stream", "lambda")
        );

        // flatMap 1번으로 한 겹 벗겨 1차원 List<String> 만들기
        List<String> tags = tagRows.stream()
                .flatMap(List::stream) // 각 "행"(List)을 펼쳐 이어 붙임 → 2차원→1차원
                .distinct()            // 중복 태그 제거
                .sorted()              // 사전순
                .toList();

        System.out.println(tags); // [java, lambda, stream]

        // =========================================================
        // [3차원] List<List<List<String>>>
        // 묶음0
        //   행0: java, stream
        //   행1: stream, lambda
        // 묶음1
        //   행0: lambda, optional
        //   행1: java, record
        // get 세 번 → 문자열
        //   (예: tagCubes.get(0).get(1).get(0) == "stream")
        // =========================================================
        List<List<List<String>>> tagCubes = List.of(
                List.of(                                    // 묶음 0
                        List.of("java", "stream"),
                        List.of("stream", "lambda")
                ),
                List.of(                                    // 묶음 1
                        List.of("lambda", "optional"),
                        List.of("java", "record")
                )
        );

        // 겹이 하나 더 있으므로 flatMap도 한 번 더
        List<String> nestedTags = tagCubes.stream()
                .flatMap(List::stream) // 3차원 → 2차원 (묶음을 펼침)
                .flatMap(List::stream) // 2차원 → 1차원 (행을 펼침)
                .distinct()
                .sorted()
                .toList();

        System.out.println(nestedTags);
    }
}
