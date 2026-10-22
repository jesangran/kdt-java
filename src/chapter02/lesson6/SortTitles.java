package chapter02.lesson6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortTitles {
    public static void main(String[] args) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("exam", "bring id"));
        posts.add(new Post("closed", "no class"));
        posts.add(new Post("quiz", "code"));

        posts.sort(new Comparator<Post>(){ //익명의 클래스를 내부에 생성(무기명 클래스)
            @Override
            public int compare(Post first, Post second) {
                return first.getTitle().compareTo(second.getTitle()); //"첫 번째 Post의 제목과 두 번째 Post의 제목을 비교해서, 어느 제목이 앞에 와야 하는지 결과를 알려줘."
            }
        });
        //람다 > posts.sort((first, second) -> first.getTitle().compareTo(second.getTitle()));

        //Comparator : implement Comparator 메서드 구현 > x > 익명의 클래스를 내부에 생성 인터페이스 구현/ 함수형 인터페이스
        //한 번만 사용할 간단한 Comparator 구현이기 때문에, 별도의 클래스 이름을 만들어서 선언하지 않고 그 자리에서 익명 클래스로 구현 > 람다로 사용

        //람다식(Lambda Expression) : 함수형 인터페이스의 메서드를 짧게 표현
    }
}
