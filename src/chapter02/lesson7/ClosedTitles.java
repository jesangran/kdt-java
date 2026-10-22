package chapter02.lesson7;

import java.util.List;

/**
 * ============================================================
 * filter + map — for문을 Stream으로 번역하는 대표 예
 * ============================================================
 *
 * ★ 머릿속에 넣을 문장 3개
 * 1) 람다 = 이름 없는 짧은 함수 (post -> post.isClosed())
 * 2) Stream = 리스트를 흘리며 filter/map을 연결하는 도구
 * 3) Stream 안의 람다 = for문의 if / getXxx() 를 인자로 넘긴 것
 *
 * ★ for문 ↔ Stream 완벽 대조 (이게 제일 중요!)
 *
 *   List<String> list = new ArrayList<>();
 *   for (Post post : posts) {
 *       if (post.isClosed()) {            // ← filter
 *           list.add(post.getTitle());    // ← map + 담기
 *       }
 *   }
 *
 *   List<String> list = posts.stream()
 *       .filter(post -> post.isClosed())  // if와 같은 자리
 *       .map(post -> post.getTitle())     // getTitle과 같은 자리
 *       .toList();                        // list.add 모으기와 같은 자리
 *
 * filter : "무엇을 남길까?" (개수↓, 타입은 Post 그대로)
 * map    : "무엇을 꺼낼까?" (타입 변경 가능 Post → String)
 *
 * ----- 메서드 체이닝 (.으로 이어 쓰는 이유) -----
 * 각 메서드가 "다음 단계용 결과"를 반환해서, 그 결과에 또 .을 붙인다.
 *   stream() → filter() → map() → toList()
 * 한 줄로 써도 같고, 줄을 나누면 처리 순서가 읽기 쉽다.
 *
 * 변수로 나누면 이런 뜻 (체이닝 = 중간 변수 생략):
 *   var s1 = posts.stream();
 *   var s2 = s1.filter(post -> post.isClosed());
 *   var s3 = s2.map(post -> post.getTitle());
 *   List<String> list = s3.toList();
 */
public class ClosedTitles {

    public static void main(String[] args) {
        List<Post> posts = BaseStreamApp.createPosts();

        // for문의 if + getTitle + list.add 를 한 흐름으로
        List<String> list = posts.stream()             // 1) List → Stream (벨트 열기)
                .filter(post -> post.isClosed())       // 2) if (마감?)  ← 람다=조건
                .map(post -> post.getTitle())          // 3) 제목만 뽑기 ← 람다=변환
                .toList();                             // 4) List<String>으로 모으기 (최종)

        // System.out.println(list);
    }
}
