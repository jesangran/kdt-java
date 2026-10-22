package chapter02.lesson6;

import java.util.ArrayList;
import java.util.List;

public class BaseCollectionApp {
    public static void main(String[] args) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("p1", "closed", "no class"));
        posts.add(new Post("p2", "exam", "bring id"));

        System.out.println("size=" + posts.size());
        System.out.println("first=" + posts.get(0).getTitle());

        //이터레이터(Iterator) : 컬렉션에 들어 있는 데이터를 하나씩 꺼내서 순회할 수 있게 해주는 객체
        //for-each = 컬렉션을 편하게 순회하는 방법 / Iterator = 컬렉션을 순회하는 도구를 직접 사용하는 방법

    }
}
