package chapter03.lesson1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilePostJson {
    public static void main(String[] args) throws IOException {
        Post post = new Post("closed", "no class");
        String json = toJson(post);

        Files.createDirectories(Path.of("data"));
        Path path = Path.of("data", "post.json");
        Files.writeString(path, json, StandardCharsets.UTF_8);

        String loaded = Files.readString(path, StandardCharsets.UTF_8);
        System.out.println("json="+loaded);

    }

    static String toJson(Post post){
        return "{\"title\":\"" + post.getTitle()
                + "\",\"body\":\"" + post.getBody() + "\"}";
    }
}
