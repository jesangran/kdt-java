package chapter03.lesson1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class SplitJson {
    public static void main(String[] args) throws IOException {
        Post a = new Post("closed", "no class");
        Post b = new Post("exam", "bring id");
        Files.createDirectories(Path.of("data"));

        Path pair = Path.of("data", "pair.json");

        Files.writeString(pair, "[" + toJson(a) + "," + toJson(b) + "]", StandardCharsets.UTF_8);
        System.out.println("json=" + Files.readString(pair, StandardCharsets.UTF_8));

        Path one = Path.of("data", "one.json");

        Files.writeString(one, toJson(a), StandardCharsets.UTF_8);

        System.out.println("one = " + Files.readString(one, StandardCharsets.UTF_8));


    }

    static String toJson(Post post) {
        return "{\"title\":\"" + post.getTitle()
                + "\",\"body\":\"" + post.getBody() + "\"}";
    }
}