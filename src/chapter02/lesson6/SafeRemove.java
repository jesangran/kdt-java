package chapter02.lesson6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SafeRemove {
    public static void main(String[] args) {

        List<Post> posts = new ArrayList<>();
        posts.add(new Post("closed", "no class"));
        posts.add(new Post("exam", "bring id"));
        posts.add(new Post("closed", "makeup"));

        //순회하면서 삭제할 때 중요
        Iterator<Post> iterator = posts.iterator();
        //post가 반복되는 객체 Iterator로 변함

        while (iterator.hasNext()){
            Post post = iterator.next();

            if(post.getTitle().equals("closed")){
                iterator.remove();
            }

            System.out.println("left="+posts.size()); //post -> iterator 원본이 따라감
            System.out.println("title="+posts.get(0).getTitle());
        }
    }
}
