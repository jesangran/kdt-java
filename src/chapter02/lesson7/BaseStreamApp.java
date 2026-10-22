package chapter02.lesson7;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * Stream 배우기 전의 "기준점"
 * ============================================================
 *
 * 역할
 * - createPosts(): 연습용 게시글 목록을 만든다. 다른 예제들이 이 메서드를 호출한다.
 * - main: 아직 Stream 없이 for문으로 출력한다.
 *
 * ★ 공부 순서 (헷갈릴 때 이 순서!)
 * 1) BaseStreamApp  — for문 (지금 파일)
 * 2) FuncTypes      — 람다 + Predicate/Function (Stream 없음)
 * 3) LambdaClosed   — for + 람다 조건 (아직 Stream 없음)
 * 4) StreamClosed   — Stream + filter (벨트 시작)
 * 5) ClosedTitles   — for문 ↔ Stream 완벽 대조 (filter+map)
 *
 * 왜 필요한가?
 * 같은 데이터를 for → 람다 → Stream 순으로 다시 처리해 보면
 * "Stream = for문의 번역"이라는 느낌이 온다.
 *
 * 데이터에 Post와 NoticePost가 섞여 있다 → List<Post> + 다형성 연습.
 */
public class BaseStreamApp {
    public static void main(String[] args) {
        List<Post> posts = createPosts();

        // ===== Stream 없이 순회하는 전통 방식 =====
        // "리스트에서 하나씩 꺼내 post.print() 호출"
        for (Post post : posts) {
            post.print();
        }
        // 나중에 Stream에서는 posts.stream().forEach(Post::print) 로 같은 일을 한다.
    }

    /**
     * 샘플 데이터 공장.
     * static이라 new BaseStreamApp() 없이 BaseStreamApp.createPosts()로 호출 가능.
     */
    public static List<Post> createPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("점심 김치찌개", "12시 출발", false));      // 모집 중
        posts.add(new Post("Java 스터디", "람다 복습", true));         // 마감
        posts.add(new NoticePost("시험 안내", "금요일 10시", true, "3강의실")); // 마감 + 장소
        posts.add(new Post("저녁 파스타", "6시 출발", false));         // 모집 중
        return posts;
    }
}
