package chapter02.lesson7;

import java.util.List;

/**
 * ============================================================
 * Stream 검색·집계 — anyMatch / count / findFirst
 * ============================================================
 *
 * ★ 이 파일의 위치 (공부 순서)
 *   StreamClosed   → filter + forEach (걸러서 출력)
 *   ClosedTitles   → filter + map + toList (걸러서 목록 만들기)
 *   StreamSearch   → "있나? / 몇 개? / 첫 번째는?" 질문하기  ← 지금
 *
 * ★ filter와 다른 점
 *   filter : 원소를 계속 흘림 (중간 연산) → 뒤에 forEach/toList 등이 필요
 *   여기 연산들 : 질문에 바로 답하는 최종 연산이 많음
 *
 * ============================================================
 * 1) anyMatch(조건) — "하나라도 있니?"
 * ============================================================
 *   boolean hasExam = posts.stream()
 *       .anyMatch(post -> post.getTitle().contains("시험"));
 *
 *   for문 번역:
 *     boolean hasExam = false;
 *     for (Post post : posts) {
 *         if (post.getTitle().contains("시험")) {
 *             hasExam = true;
 *             break; // 하나 찾으면 더 안 봐도 됨
 *         }
 *     }
 *
 *   비슷한 것
 *     allMatch : 전부 조건 만족? (StreamPipelineDemo에도 나옴)
 *     noneMatch: 하나도 조건 안 맞음?
 *
 * ============================================================
 * 2) filter + count — "몇 개니?"
 * ============================================================
 *   long closedCount = posts.stream()
 *       .filter(Post::isClosed)
 *       .count();
 *
 *   Post::isClosed  ==  post -> post.isClosed()  (메서드 참조)
 *
 *   for문 번역:
 *     long closedCount = 0;
 *     for (Post post : posts) {
 *         if (post.isClosed()) closedCount++;
 *     }
 *
 * ============================================================
 * 3) findFirst + orElse — "첫 번째 가져와. 없으면 기본값"
 * ============================================================
 *   findFirst() → Optional 반환
 *   이유: 조건에 맞는 글이 있을 수도, 없을 수도 있음 (null 대신 상자)
 *
 *   .findFirst().orElse("저녁 모임이 없다.")
 *   = OptionalDemo의 orElse와 같은 패턴
 *
 *   for문 번역:
 *     String firstDinner = "저녁 모임이 없다.";
 *     for (Post post : posts) {
 *         if (post.getTitle().contains("저녁")) {
 *             firstDinner = post.getTitle();
 *             break;
 *         }
 *     }
 */
public class StreamSearch {

    public static void main(String[] args) {

        List<Post> posts = BaseStreamApp.createPosts();

        // ----- Q1. 존재 여부 -----
        boolean hasExam = posts.stream()
                .anyMatch(post -> post.getTitle().contains("시험")); // 최종: boolean

        // ----- Q2. 개수 -----
        long closedCount = posts.stream()
                .filter(Post::isClosed) // 중간: 마감만 남김 (메서드 참조)
                .count();               // 최종: 남은 개수

        // ----- Q3. 첫 번째 + 기본값 -----
        String firstDinner = posts.stream()
                .filter(post -> post.getTitle().contains("저녁")) // 중간
                .map(post -> post.getTitle())                    // 중간: Post → 제목
                .findFirst()                 // 최종: Optional<String> (있을 수도/없을 수도)
                .orElse("저녁 모임이 없다."); // Optional이 비었을 때 대체 문자열

        System.out.println("시험 글 존재: " + hasExam);
        System.out.println("마감 글 수: " + closedCount);
        System.out.println("첫 저녁 글: " + firstDinner);
    }
}
