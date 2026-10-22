package chapter01;

import java.util.Scanner;

public class ParsePrice {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // 문자+문자 = 문자 문자
        ////ex. String ctrl + p > 어떤 인자(argument)가 들어갈 수 있는지 알 수 있다
        System.out.println("price?");
        String raw = sc.nextLine();
        int price = Integer.parseInt(raw);
        System.out.println("price="+price);

        System.out.println("price2?");
        int price2 = Integer.parseInt(sc.nextLine());
        //괄호가 들어가면 안쪽에서 바깥쪽으로 수행

        int total = 5500;
        String txt = String.valueOf(total);

        /*
        * double -> (int) double 소수점을 정수로
        * int -> String.valueOf(int) 숫자를 문자열로, 문자열로 형변환할때 String.valueOf로 사용하면 됨
        * String -> Integer.parseInt(String) 문자열을 숫자로
        *        ->Long.parseLong(String) 문자열을 long
        *        ->Double.parseDouble(String) 문자열을 double
        *        ->Boolean.parseBoolean(String) 문자열을 boolean
        * 자동채우기 alt+enter
        * */

    }
}
