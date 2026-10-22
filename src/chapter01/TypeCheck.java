package chapter01;

public class TypeCheck {
    public static void main(String[] args) {

        int price = 4500; // 원 단위 정수
        double rating = 4.5; // 점이 있는 점수
        boolean soldOut = false; // true 또는 false만, 소문자
        char seat = 'A'; // 글자 하나
        ////////////////////////////////////////////예약어

        String menu = "kimbap"; // 글자 여러 개, 기본 타입 아님(예약어가 아님)

        System.out.println("price=" + price);
        System.out.println("rating=" + rating);
        System.out.println("soldOut=" + soldOut);
        System.out.println("seat=" + seat);
        System.out.println("menu=" + menu);

        int kimbap = 4000;
        int water = 1000;
        System.out.println("wrong=" + kimbap + water); // 이어 붙임 > 문자열이 된다
        System.out.println("연산 확인 total="+(kimbap+water)); // 괄호안부터 연산 후 실행

        int total = kimbap + water; // 새로운 변수 생성 후 더함
        System.out.println("total=" + total); // 그다음 붙임
    }
}
