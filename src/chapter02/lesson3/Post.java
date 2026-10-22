package chapter02.lesson3;

public class Post {

    private static int cnt = 0;
    //접근제한자 static옵션 final 타입 변수 = 값;

    private String title;                        // 문제를 확인하기 위한 임시 public 필드
    private String body;
    private final String id;
    private boolean closed;

    public void print() {
        System.out.println("title=" + title);
        System.out.println("body=" + body);
    }

    public String getId(){
        return this.id; //getter
    }

    public void changeTitle(String next){
        if(this.closed) { //false
            System.out.println("reject=closed");
            return;
        }

        if (next == null || next.isEmpty()){
            System.out.println("reject=empty-title");
            return;
        }

        this.title = next;
        //this.title = title;
    }

    public void close(){
        this.closed = true; //getter
    }

    public boolean isClosed() {
        return closed; //setter
    }

    //객체의 데이터를 외부에서 마음대로 건드리지 못하게 하고, 필요한 규칙을 거쳐 접근하도록 만드는 것 : 캡슐화(Encapsulation)

    //생성자
    public Post(String title, String body) {

        if(title == null || title.isEmpty()){
            throw new IllegalArgumentException("empty-title");
        }

        cnt = cnt +1;
        this.id = "p" + cnt;
        this.title = title;
        this.body = body;
        this.closed = false;
    }

    public Post(String id, String title, String body){

        if(title == null || title.isEmpty()){
            throw new IllegalArgumentException("empty-title");
        }
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("empty-id");
        }

        this.id = id;
        this.title = title;
        this.body = body;
        this.closed = false;
        cnt = cnt + 1;

    }

    public static int getCnt(){
        return cnt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    //equals -> 객체의 주소 비교, String의 경우 재정의하여 값을 비교
    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true; //Post의 위치주소와 다른 Post의 위치 주소가 같다면 true반환
        }

        if(!(o instanceof Post)) { //instanceof 객체의 타입을 확인
            return false;
        }

        Post that = (Post) o; //Post type으로 형변환, 같은지의 판단 기준을 이 구문에서 해석
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
        //equals()가 true인 두 객체는 반드시 같은 hashCode()를 가져야 한다.
        //equals()비교 > hashCode() 사용  > 값 일치
    }
}
