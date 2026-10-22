package chapter02.lesson7;

import java.util.List;

/**
 * ============================================================
 * Stream 첫걸음 — 벨트를 연다 (filter / forEach / toList)
 * ============================================================
 *
 * ★ 공부 순서에서의 위치
 *   FuncTypes / LambdaClosed → 람다만 (조건·변환을 변수에)
 *   StreamClosed             → Stream 시작 (지금 파일)
 *   ClosedTitles             → filter + map
 *   StreamSearch             → anyMatch / count / findFirst
 *   StreamPipelineDemo       → 중간 연산 여러 개 연결
 *   FlatMapDemo / GroupingDemo → 펼치기 / 묶기
 *
 * ★ 람다 vs Stream (꼭 구분!)
 *   람다  = 작업 설명서  (post -> post.isClosed())
 *   Stream = 그 설명서를 순서대로 실행하는 벨트
 *   → filter/forEach가 람다를 "받는다"
 *
 * ★ for문과 같은 일
 *   for (Post post : posts) {
 *       if (post.isClosed()) {
 *           System.out.println(post.getTitle());
 *       }
 *   }
 *   ↓
 *   posts.stream()
 *       .filter(post -> post.isClosed())
 *       .forEach(post -> System.out.println(post.getTitle()));
 *
 * ★ 중간 vs 최종
 *   filter     … 중간 (예약)
 *   forEach, toList … 최종 (여기서 실행)
 *   최종이 없으면 벨트만 만들고 끝 → 화면에 아무것도 안 나올 수 있음
 *
 * ★ stream()은 원본 List를 수정하지 않는다.
 */
public class StreamClosed {

    public static void main(String[] args) {
        List<Post> posts = BaseStreamApp.createPosts();

        // --- 예제 1: 마감 글 제목만 출력 (for문의 if + println) ---
        posts.stream()                                           // List → Stream
                .filter(post -> post.isClosed())                 // 중간: 마감만 (람다=Predicate)
                .forEach(post -> System.out.println(post.getTitle())); // 최종: 출력 (람다=Consumer)

        // --- 예제 2: filter 두 번 = AND 조건 → List로 모으기 ---
        // 마감이고, 제목에 "시험" 포함
        List<Post> a = posts.stream()
                .filter(post -> post.isClosed())
                .filter(post -> post.getTitle().contains("시험"))
                .toList(); // 최종: 새 List (원본 posts는 그대로)

        System.out.println(a); // Post에 toString 없으면 주소처럼 보일 수 있음
    }
}
