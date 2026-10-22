package chapter03.lesson1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// 과제 2-1: Post 두 개를 JSON 배열로 이어 붙여 data/cafe.json 에 저장
public class CafeSave {
    public static void main(String[] args) throws IOException {
        Post a = new Post("pork", "6000won");
        Post b = new Post("water", "1000won");

        // [직렬화] 두 객체를 "[" + 객체 + "," + 객체 + "]" 로 이어 붙임
        // (사이만 콤마, 마지막엔 콤마 없음)
        String json = "[" + toJson(a) + "," + toJson(b) + "]";

        Files.createDirectories(Path.of("data"));
        Path path = Path.of("data", "cafe.json");
        Files.writeString(path, json, StandardCharsets.UTF_8); // 파일에 저장

        // [역직렬화] 파일 → 문자열로 다시 읽기
        String loaded = Files.readString(path, StandardCharsets.UTF_8);
        System.out.println("json=" + loaded);
        System.out.println("exists=" + Files.exists(path));
    }

    // Post 1개 → {"title":"...","body":"..."}
    static String toJson(Post post) {
        return "{\"title\":\"" + post.getTitle()
                + "\",\"body\":\"" + post.getBody() + "\"}";
    }
}
