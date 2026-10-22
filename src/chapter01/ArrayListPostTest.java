package chapter01;

import java.util.ArrayList;

public class ArrayListPostTest {
    public static void main(String[] args) {

        // 에레이리스트를 만드는데 타입은 Post만 받을 수 있다., 그리고 새 포스트 객체를 3개 만들어서
        // 해당 어레이리스트에 추가한다.
        // 반복문으로 그 3가지 각 객체에 존재하는 필드 2가지를 출력한다.

        ArrayList<Post> posts = new ArrayList<>();

        Post post1 = new Post();
        Post post2 = new Post();
        Post post3 = new Post();

        post1.title = "A";
        post1.body = "aa";
        post2.title = "B";
        post2.body = "bb";
        post3.title = "C";
        post3.body = "cc";

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);

        //posts.add(new Post("필드1","필드2"));

        System.out.println("for문");
        for(int i = 0; i < posts.size(); i++) {
            System.out.println(posts.get(i).title);
            System.out.println(posts.get(i).body);
        }

        System.out.println("향상된 for문"); //// 향상된 for문 : 객체 자체를 가지고 옴
        for (Post post : posts) {
            System.out.println(post.title);
            System.out.println(post.body);
        }

    }
}
