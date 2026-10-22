package chapter02.lesson3;

public class PostWithAuthor {
    private final String title;
    private final Author author;               // has-a. 필드 타입이 객체

    //생성자가 호출되면서 초기화
    public PostWithAuthor(String title, Author author) {
        this.title = title;
        this.author = author; //조합
    }//Author가 꼭 필요

    public void print() {
        System.out.println("title=" + title);
        System.out.println("author=" + author.getName());
    }
}
