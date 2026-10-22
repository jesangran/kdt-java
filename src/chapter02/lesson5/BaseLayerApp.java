package chapter02.lesson5;

public class BaseLayerApp {
    public static void main(String[] args) {
        PostRepository repository = new MemoryPostRepository();
        PostService service = new PostService(repository);
        service.register("closed", "body");
        service.printAll();
    }
}
