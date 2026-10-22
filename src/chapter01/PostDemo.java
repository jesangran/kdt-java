package chapter01;

public class PostDemo {
    public static void main(String[] args) {

        Post post = new Post(); // Post 객체를 생성, post가 그 객체를 참조 > 인스턴스화
        post.body = "본문1";
        post.title = "제목1";

        Post post1 = new Post();
        post1.body = "본문2";
        post1.title = "제목2";

        //post1 != post2 != post3 서로 다른 객체

        Post post3 = new Post();
        System.out.println(post3.title); // 값을 넣지 않았기때문에 null 출력

        /*
        * 객체 생성 시 메모리에 방 생성 > 주소가 만들어짐
        * a라는 변수를 생성시 A라는 주소를 바라보고 있음
        * A b = new A() > 동일하게 A라는 주소를 바라보고 있음
        * */

        System.out.println(post3.isBody());

        System.out.println(post1.titleBody()+post1.titlePlus());

        post.pringArg("good");

        post.addTitle(post.body, post.title);

    }
}
