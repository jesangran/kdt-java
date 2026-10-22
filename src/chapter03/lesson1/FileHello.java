package chapter03.lesson1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHello {
    public static void main(String[] args) throws IOException {
        Path dir = Path.of("data"); //파일이나 경로가 어디있는지 알려주는 자바 객체
        //실행 위치 기준 "data" 폴더를 가리키는 Path 객체 생성
        Files.createDirectories(dir);//data 폴더가 없으면 생성

        Path path = Path.of("data", "hello.txt"); //data 안의 hello.txt 경로 생성

        Files.writeString(path,"Kimbap\n", StandardCharsets.UTF_8); //디폴트인코딩
        //경로에 문자열 "Kimbap" + 줄바꿈(\n)을 UTF-8으로 쓰기, 없으면 만들고 있으면 내용 덮어 쓰기
        String text = Files.readString(path, StandardCharsets.UTF_8);
        //같은 파일을 UTF-8로 읽어 text에 넣기(방금 쓴 "Kimbap\n"이 들어옴)

        System.out.println("text= "+ text.stripTrailing());//끝 공백/줄바꿈을 자른 뒤 출력
        System.out.println("exists= "+Files.exists(path)); //파일유무 boolean, hello.txt가 있는지 확인해 true/false를 출력
        System.out.println("path=" +path.toAbsolutePath()); //상대 경로를 절대 경로로 바꿔 출력
    }
}
