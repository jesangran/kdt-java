package chapter01;

import java.util.Scanner;

public class WhyScanner {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("input ready!"); //출력

        String line = input.nextLine(); // 한줄로 외부입력 받기
        System.out.println("ready!"); //출력
        System.out.println("menu: "+line); //출력

        String line2 = input.nextLine();
        System.out.println("menu: "+line2); //출력

        /*
        * 문자열 String은 기본타입이 아님
        * input.nextLine(); : String (문자열) 최종 반환
        * 형변환을 통해 type을 강제로 바꿀 수 있음
        * */

    }
}
