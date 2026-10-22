package chapter03.lesson1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

// FileBoardSave와 같은 흐름 + 빈 목록([])도 한 번 저장해 보는 예제
//
// [직렬화]   Post 리스트 → JSON 문자열 → writeString(파일 저장)
// [역직렬화] readString(파일 → 문자열)  ※ Post 객체로 되돌리기는 아직 안 함
public class RollJson {
    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of("data")); // data 폴더 없으면 생성

        // 1) 빈 목록 직렬화 → empty.json 에 "[]" 저장
        ArrayList<Post> emptyPosts = new ArrayList<>();
        Path path = Path.of("data", "empty.json");
        Files.writeString(path, toArrayJson(emptyPosts), StandardCharsets.UTF_8); // 직렬화 후 저장
        System.out.println("empty=" + Files.readString(path, StandardCharsets.UTF_8)); // 역직렬화(문자열까지)

        // 2) 게시글 3개 직렬화 → roll.json (FileBoardSave와 동일, 파일명만 다름)
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("closed", "no class"));
        posts.add(new Post("exam", "bring id"));
        posts.add(new Post("kimbap", "sold out"));

        String json = toArrayJson(posts); // [직렬화] 객체 → JSON 문자열
        path = Path.of("data", "roll.json");
        Files.writeString(path, json, StandardCharsets.UTF_8); // [직렬화] 파일에 쓰기

        String loaded = Files.readString(path, StandardCharsets.UTF_8); // [역직렬화] 파일 → 문자열
        System.out.println("json=" + loaded);
        System.out.println("exists=" + Files.exists(path));
    }

    // [직렬화] Post 1개 → {"title":"...","body":"..."}
    static String toJson(Post post) {
        return "{\"title\":\"" + post.getTitle()
                + "\",\"body\":\"" + post.getBody() + "\"}";
    }

    // [직렬화] Post 리스트 → [{...},{...}]  (비어 있으면 "[]")
    static String toArrayJson(ArrayList<Post> posts) {
        String json = "[";
        for (int i = 0; i < posts.size(); i++) {
            if (i > 0) {
                json = json + ",";
            }
            json = json + toJson(posts.get(i));
        }
        json = json + "]";
        return json;
    }
}
