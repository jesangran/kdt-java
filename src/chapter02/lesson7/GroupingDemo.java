package chapter02.lesson7;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ============================================================
 * Collectors.groupingBy / Collectors.joining
 * ============================================================
 *
 * collect(...) = Stream을 "최종 결과 모양"으로 모으는 최종 연산
 * Collectors  = 그 모양을 정해 주는 도구 모음
 *
 * toList()          → List로 모으기
 * groupingBy(...)   → Map으로 "같은 키끼리" 묶기
 * joining(...)      → 문자열 하나로 이어 붙이기
 *
 * ----- 수업 데이터 (AdvancedBaseApp) -----
 *   점심, 시험, 점심, 휴강
 *   → "점심"이 두 번이라 그룹핑이 눈에 보임
 *
 * ============================================================
 * 1) Collectors.groupingBy(키를_뽑는_함수)
 * ============================================================
 *
 * 의미: "이 기준으로 바구니를 나눠 담아줘"
 *
 *   posts.stream()
 *       .collect(Collectors.groupingBy(post -> post.getTitle()));
 *
 *   결과 타입: Map<String, List<Post>>
 *   결과 그림:
 *     "점심" → [Post(점심/김밥), Post(점심/제육볶음)]
 *     "시험" → [Post(시험/...)]
 *     "휴강" → [Post(휴강/...)]
 *
 * for문 번역:
 *   Map<String, List<Post>> byTitle = new HashMap<>();
 *   for (Post post : posts) {
 *       String key = post.getTitle();
 *       if (!byTitle.containsKey(key)) {
 *           byTitle.put(key, new ArrayList<>());
 *       }
 *       byTitle.get(key).add(post);
 *   }
 *
 * ============================================================
 * 2) Collectors.groupingBy(키, Collectors.counting())
 * ============================================================
 *
 * 의미: "바구니에 객체 대신, 몇 개인지만 세어줘"
 *
 *   .collect(Collectors.groupingBy(post -> post.getTitle(), Collectors.counting()));
 *
 *   결과 타입: Map<String, Long>
 *   결과 그림:
 *     "점심" → 2
 *     "시험" → 1
 *     "휴강" → 1
 *
 * 두 번째 인자(counting)를 "다운스트림 컬렉터"라고 부른다.
 * = 그룹 안에서 무엇을 할지(목록 유지 vs 개수만 등)
 *
 * ============================================================
 * 3) Collectors.joining(구분자)
 * ============================================================
 *
 * 의미: "문자열들을 구분자로 이어 하나의 String으로"
 * ※ joining은 스트림 원소가 문자열(또는 CharSequence)이어야 해서
 *    보통 앞에 .map(post -> post.getTitle()) 을 붙인다.
 *
 *   posts.stream()
 *       .map(post -> post.getTitle())
 *       .collect(Collectors.joining(", "));
 *
 *   결과: "점심, 시험, 점심, 휴강"
 *
 * for문 번역:
 *   StringJoiner joiner = new StringJoiner(", ");
 *   for (Post post : posts) {
 *       joiner.add(post.getTitle());
 *   }
 *   String joined = joiner.toString();
 *
 * 실무 감각
 *   groupingBy → 카테고리별 목록/통계
 *   joining    → 화면·로그에 "A, B, C" 한 줄로 보여 주기
 */
public class GroupingDemo {
    public static void main(String[] args) {

        /*
        posts.add(new Post("점심", "김밥", false));
        posts.add(new Post("시험", "신분증 준비", true));
        posts.add(new NoticePost("점심", "제육볶음", false, "학생식당"));
        posts.add(new Post("휴강", "보강 일정 확인", true));
        *
        * */
        List<Post> posts = AdvancedBaseApp.createPosts();

        // 1) 제목(키)별로 Post들을 List 바구니에 담기
        //    Map<"점심", [글, 글]> 형태
        Map<String, List<Post>> byTitle = posts.stream()
                .collect(Collectors.groupingBy(post -> post.getTitle()));

        // 2) 제목별로 "몇 개?"만 세기
        //    Map<"점심", 2L> 형태
        Map<String, Long> counts = posts.stream()
                .collect(Collectors.groupingBy(post -> post.getTitle(), Collectors.counting()));

        // 3) 제목 문자열만 뽑아서 ", "로 이어 붙이기
        String joined = posts.stream()
                .map(post -> post.getTitle())              // Post → String (joining 전에 필요)
                .collect(Collectors.joining(", "));        // "점심, 시험, 점심, 휴강"

        System.out.println("점심 묶음 크기: " + byTitle.get("점심").size()); // 2
        System.out.println("점심 개수: " + counts.get("점심"));             // 2
        System.out.println("제목: " + joined);
    }
}
