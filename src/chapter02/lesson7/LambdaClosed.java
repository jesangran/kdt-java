package chapter02.lesson7;

import java.util.List;
import java.util.function.Predicate;

/**
 * ============================================================
 * 람다 + Predicate (아직 Stream 없음!)
 * ============================================================
 *
 * ★ 이 파일의 목적
 * Stream 없이 "조건을 변수에 담는 법"만 익힌다.
 * Stream은 StreamClosed / ClosedTitles 에서 본다.
 *
 * ----- 예전: 조건을 메서드로 -----
 *   public static boolean isClosed(Post post) {
 *       return post.isClosed();
 *   }
 *   if (isClosed(post)) { ... }
 *
 * ----- 지금: 같은 일을 람다로 변수에 -----
 *   Predicate<Post> isClosed = post -> post.isClosed();
 *   if (isClosed.test(post)) { ... }
 *
 * 읽는 법
 *   post -> post.isClosed()
 *   = "Post 하나 주면, 마감인지(true/false) 알려줄게"
 *
 * 왜 배우나?
 * 나중에 Stream.filter(isClosed) 처럼
 * "조건 자체를 인자로 넘기기" 위한 밑거름이다.
 */
public class LambdaClosed {

    public static void main(String[] args) {
        List<Post> posts = BaseStreamApp.createPosts();

        // 람다 = 작은 함수를 Predicate 상자에 담음
        Predicate<Post> isClosed = post -> post.isClosed();
        // 같은 뜻(메서드 참조): Predicate<Post> isClosed = Post::isClosed;

        // 여기는 그냥 for문! Stream이 아니다.
        for (Post post : posts) {
            if (isClosed.test(post)) { // 상자에 담은 조건을 검사
                System.out.println(post.getTitle());
            }
        }
    }

//    public static boolean isClosed(Post post) {
//        return post.isClosed();
//    }
}
