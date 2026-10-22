package chapter03.lesson1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

// 게시글 여러 개를 JSON으로 만들어 파일에 저장하고, 다시 읽어 확인하는 예제
//
// [직렬화]   메모리의 객체(Post 리스트) → 저장/전송 가능한 형태(JSON 문자열) → 파일
// [역직렬화] 파일 → 다시 프로그램에서 쓸 수 있는 형태(여기선 JSON 문자열까지)
//            ※ 완전 역직렬화는 문자열을 다시 Post 리스트로 파싱하는 단계까지 포함
//              (이 예제는 readString으로 문자열만 되돌림)
public class FileBoardSave {
    // throws IOException: 파일 쓰기/읽기는 실패할 수 있어서, 예외를 main 밖으로 넘김
    public static void main(String[] args) throws IOException {
        // 아직 메모리에만 있는 자바 객체들 (프로그램 종료 시 사라짐)
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("closed", "no class"));
        posts.add(new Post("exam", "bring id"));
        posts.add(new Post("kimbap", "sold out"));

        // ===== 직렬화 시작: 객체 → JSON 문자열 =====
        // 예: [{"title":"closed","body":"no class"},...]
        String json = toArrayJson(posts);

        Files.createDirectories(Path.of("data")); // data 폴더 없으면 생성
        Path path = Path.of("data", "board.json"); // 저장할 파일 경로
        // JSON 문자열을 파일에 씀 → 직렬화 결과물이 디스크에 남음
        Files.writeString(path, json, StandardCharsets.UTF_8);
        // ===== 직렬화 끝 =====

        // ===== 역직렬화(일부): 파일 → 문자열 =====
        // 파일에서 다시 읽어옴. 지금은 String까지. (Post 객체 복원은 아직 X)
        String loaded = Files.readString(path, StandardCharsets.UTF_8);
        System.out.println("json=" + loaded);
        System.out.println("exists=" + Files.exists(path)); // 파일 존재 여부
    }

    // [직렬화 도우미] Post 1개 → JSON 객체 문자열
    // 예: {"title":"kimbap","body":"sold out"}
    // ※ 연습용으로 직접 이어 붙임. 실무에서는 Jackson 등 라이브러리 사용
    static String toJson(Post post) {
        return "{\"title\":\"" + post.getTitle()
                + "\",\"body\":\"" + post.getBody() + "\"}";
    }

    // [직렬화 도우미] Post 리스트 → JSON 배열 문자열
    // 흐름: "[" → 각 Post를 toJson → 사이에 "," → "]"
    static String toArrayJson(ArrayList<Post> posts) {
        String json = "[";
        for (int i = 0; i < posts.size(); i++) {
            if (i > 0) {
                json = json + ","; // 두 번째부터 콤마
            }
            json = json + toJson(posts.get(i));
        }
        json = json + "]";
        return json;
    }
}
