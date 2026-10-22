package chapter03.lesson1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class OverwriteJson {
    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of("data"));
        Path path = Path.of("data", "price.json");

        // 1) menu/rice 쓰기 → 읽기 → 출력
        Post post = new Post("menu", "rice");
        String json = toJson(post);
        Files.writeString(path, json, StandardCharsets.UTF_8);
        String loaded = Files.readString(path, StandardCharsets.UTF_8);
        System.out.println("json=" + loaded);

        // 2) 같은 파일에 soup/hot 덮어쓰기 → 다시 읽기 → 출력
        post = new Post("soup", "hot");
        json = toJson(post); // 새 Post로 JSON을 다시 만들어야 함
        Files.writeString(path, json, StandardCharsets.UTF_8);
        loaded = Files.readString(path, StandardCharsets.UTF_8);
        System.out.println("json=" + loaded);

        // 3) post.json은 수정하지 않고 존재 여부만 확인
        System.out.println("kept=" + Files.exists(Path.of("data", "post.json")));
    }

    static String toJson(Post post) {
        return "{\"title\":\"" + post.getTitle()
                + "\",\"body\":\"" + post.getBody() + "\"}";
    }
}
