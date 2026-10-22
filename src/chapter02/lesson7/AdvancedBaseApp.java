package chapter02.lesson7;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 그룹핑용 데이터 + 메서드 참조
 * ============================================================
 *
 * BaseStreamApp과 비슷한 "데이터 공장"이지만,
 * 제목 "점심"이 두 번 나오도록 만들어서 GroupingDemo에서 묶어 보기 좋다.
 *
 * 메서드 참조 (Method Reference)
 *   createPosts().forEach(Post::print);
 * 는 아래와 완전히 같은 의미다.
 *   createPosts().forEach(post -> post.print());
 *
 * 읽는 법: "Post의 print 메서드를 각 원소에 적용해라"
 * Stream/람다를 짧게 쓸 때 자주 나온다.
 */
public class AdvancedBaseApp {
    public static List<Post> createPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("점심", "김밥", false));
        posts.add(new Post("시험", "신분증 준비", true));
        posts.add(new NoticePost("점심", "제육볶음", false, "학생식당")); // 제목 중복
        posts.add(new Post("휴강", "보강 일정 확인", true));
        return posts;
    }

    public static void main(String[] args) {
        // forEach는 Consumer를 받는다. Post::print 가 그 Consumer 역할.
        createPosts().forEach(Post::print);
    }
}
