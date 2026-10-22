package chapter02.lesson5;

public class BaseApp {
    public static void main(String[] args) {
        Post post = new Post("exam", "bring id");
        post.print();
        System.out.println("id=" + post.getId());
    }
}
