package chapter03.lesson1;

public class Post {
    private final String title;
    private final String body;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }
}
