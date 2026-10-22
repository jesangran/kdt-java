package chapter03.lesson1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TwiceWrite {
    public static void main(String[] args) throws IOException {

        /*Path dir = Path.of("data");
        Files.createDirectories(dir);
        Path path = Path.of("data", "tray.txt");*/

        Files.createDirectories(Path.of("data"));
        Path path = Path.of("data", "tray.txt");

        // 1) kimbap 쓰기 → 읽기 → 출력
        Files.writeString(path, "kimbap\n", StandardCharsets.UTF_8);
        //String text = Files.readString(path, StandardCharsets.UTF_8);
        //System.out.println("text=" + text.stripTrailing());
        System.out.println("text="+Files.readString(path,StandardCharsets.UTF_8).stripTrailing());

        // 2) cookie로 덮어쓰기(append 아님) → 다시 읽기 → 출력
        Files.writeString(path, "cookie", StandardCharsets.UTF_8);
        //text = Files.readString(path, StandardCharsets.UTF_8);
        //System.out.println("text=" + text.stripTrailing());
        System.out.println("text="+Files.readString(path,StandardCharsets.UTF_8).stripTrailing());

        // 3) tray-missing.txt는 만들지 않고, 존재 여부만 확인
        //Path missing = Path.of("data", "tray-missing.txt");
        //System.out.println("missing=" + Files.exists(missing));
        System.out.println("missing="+Path.of("data", "tray-missing.txt"));
    }
}
