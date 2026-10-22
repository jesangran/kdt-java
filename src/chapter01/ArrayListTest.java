package chapter01;

import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> titles = new ArrayList<>(); //길이가 동적

        //배열이라서 왼쪽에서 오른쪽으로 채워짐, 값을 추가할 경우 배열.add("값");
        titles.add("closed");
        titles.add("exam");
        titles.add("holiday");

        //길이출력
        System.out.println(titles.size());
        //값을 가져올때 (0 > 1 > 2 > ...), getFist(), getLast()
        System.out.println(titles.get(0)); //인덱스 0 값 호출

        //System.out.println(titles.get(3)); //titles 인덱스에 요소가 없기때문에 err(인수가 범위를 벗어남)
        //titles.get.class() > titles 객체가 어떤 클래스를 사용하는지 확인

        //값수정
        System.out.println(titles.set(1, "makeup")); //titles 1번째 인덱스에 있는 값을 수정

        //값삭제
        System.out.println(titles.remove(0)); // titles 0번째 인덱스에 있는 값을 삭제

    }
}
