package chapter02.lesson3;

public class PostDomainDemo {
    public static void main(String[] args) {

        Post post = new Post("closed", "body");
        post.changeTitle(""); //err
        post.changeTitle("exam");
        post.close();
        post.changeTitle("quiz");

        post.print();

    }
}
