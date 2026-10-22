package chapter02.lesson5;

import java.util.ArrayList;
import java.util.List;

public class MemoryPostRepository implements PostRepository {
    private final List<Post> posts = new ArrayList<>();

    @Override
    public void save(Post post) { posts.add(post); }

    @Override
    public ArrayList<Post> findAll() { return new ArrayList<>(posts); }
}
