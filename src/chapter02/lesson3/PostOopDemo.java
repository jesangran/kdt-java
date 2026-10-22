package chapter02.lesson3;

public class PostOopDemo {
    public static void main(String[] args) {
        //Post post = new Post();
        //post.title = "closed";
        //post.body = "no class";

        Post post = new Post("closed", "no class");

        post.print();

        NoticePost noticePost = new NoticePost("t1","t2","t3");
        noticePost.print();

    }
}
