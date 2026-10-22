package chapter02.lesson7;

/**
 * ============================================================
 * 도메인(데이터) 클래스의 기본 모양
 * ============================================================
 *
 * 백엔드에서 "게시글", "회원", "주문"처럼 한 건의 정보를 담는 클래스를
 * 도메인이라고 부릅니다. Stream 예제들은 전부 이 Post를 재료로 씁니다.
 *
 * 초급이 기억할 포인트
 * 1) 필드는 private → 밖에서는 getter로만 읽는다.
 * 2) final → 생성자에서 한 번만 넣고 이후 바꾸지 않는다.
 *    (closed만 final이 아님 → 마감 여부만 바뀔 수 있음)
 * 3) static sequence → "객체마다"가 아니라 "클래스 전체"가 공유하는 카운터.
 *    new Post를 할 때마다 1, 2, 3... 식으로 id를 만든다.
 * 4) isClosed()처럼 boolean getter는 getClosed가 아니라 isXXX 관례를 쓴다.
 */
public class Post {
    // static: Post 객체가 몇 개든 이 숫자는 하나뿐
    private static int sequence = 1;

    private final String id;
    private final String title;
    private final String body;
    private boolean closed; // 마감 여부만 나중에 변경 가능

    public Post(String title, String body, boolean closed) {
        // "p1", "p2"... 자동 부여. sequence++ 는 쓰고 나서 1 증가
        this.id = "p" + sequence++;
        this.title = title;
        this.body = body;
        this.closed = closed;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    /** 마감이면 true. Stream의 filter(Post::isClosed)에서 자주 씀 */
    public boolean isClosed() {
        return closed;
    }

    /** 상태를 바꾸는 메서드(setter 대신 의미 있는 이름) */
    public void close() {
        closed = true;
    }

    /**
     * 화면 출력용. toString()을 오버라이드하지 않고 직접 출력한다.
     * NoticePost가 이 메서드를 오버라이드해서 장소까지 추가로 찍는다.
     */
    public void print() {
        System.out.println(id + " | " + title + " | " + (closed ? "마감" : "모집 중"));
    }
}
