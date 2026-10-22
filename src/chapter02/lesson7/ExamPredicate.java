package chapter02.lesson7;

import java.util.List;
import java.util.function.Predicate;

/**
 * ============================================================
 * Predicate로 "검색 조건" 만들기
 * ============================================================
 *
 * LambdaClosed가 "마감 여부"였다면, 여기는 "제목에 시험이 포함되나?"다.
 * 패턴은 완전히 같다.
 *
 *   1) Predicate 변수에 람다로 조건 정의
 *   2) for문으로 돌면서 test()로 걸러냄
 *
 * 실무 감각
 * - 검색어, 상태 필터 같은 걸 if문에 직접 하드코딩하지 않고
 *   "조건 객체"로 빼 두면 나중에 Stream.filter에도 그대로 넣을 수 있다.
 *
 *   posts.stream().filter(containsExam).forEach(...)
 */
public class ExamPredicate {

    public static void main(String[] args) {
        List<Post> posts = BaseStreamApp.createPosts();

        // 제목에 "시험"이 들어있으면 true
        Predicate<Post> containsExam = post -> post.getTitle().contains("시험");

        for (Post post : posts) {
            if (containsExam.test(post)) {
                System.out.println(post.getTitle());
            }
        }
    }
}
