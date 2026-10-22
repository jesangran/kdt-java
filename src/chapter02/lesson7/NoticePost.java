package chapter02.lesson7;

/**
 * ============================================================
 * 상속(extends)과 오버라이드(Override)
 * ============================================================
 *
 * 왜 상속하나?
 * - 게시글(Post)과 공지(NoticePost)는 "제목/본문/마감"은 같고,
 *   공지만 "장소"가 더 있다 → 공통은 부모, 추가만 자식.
 *
 * 핵심 문법
 * 1) extends Post → Post의 필드/메서드를 물려받는다.
 * 2) super(...) → 부모 생성자를 먼저 호출해야 한다. (자식이 부모 필드를 세팅)
 * 3) @Override print() → 부모와 "같은 이름"의 메서드를 자식 버전으로 바꾼다.
 * 4) super.print() → 부모 출력을 그대로 쓴 다음, 자식만의 내용을 덧붙인다.
 *
 * 다형성(중요)
 * List<Post> posts 안에 new NoticePost(...)도 넣을 수 있다.
 * 타입이 Post여도 실행 시 실제 객체가 NoticePost면 자식의 print()가 호출된다.
 */
public class NoticePost extends Post {
    private final String place; // 공지에만 있는 추가 정보

    public NoticePost(String title, String body, boolean closed, String place) {
        // 부모(Post)가 가진 title, body, closed, id 세팅을 맡김
        super(title, body, closed);
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public void print() {
        super.print(); // 먼저 Post의 "id | 제목 | 마감여부" 출력
        System.out.println("장소: " + place); // 그다음 공지 전용 줄
    }
}
