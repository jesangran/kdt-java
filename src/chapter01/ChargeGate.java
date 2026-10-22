package chapter01;

public class ChargeGate {
    public static void main(String[] args) {

        //boolean a = true;
        /*true / false 는 예약어라서 소문자로 작성, =은 초기화(할당)
            == 같다 false == false
            != 다르다 > 크다(초과) >= 크거나 같다(이상) < 작다(미만) <= 작거나 같다(이하)
            && and , || or
        */

        //System.out.println(500 < 0); //boolean으로 반환

//        int charge = 500;
//
//        if(charge < 0){ //조건
//            //조건문 안에 들어가는 것은 true
//            System.out.println("short!!");
//        }else{
//            System.out.println("charge="+charge);
//        }

        int score = 76;

        char grade;

        if (score >= 90) {
            grade = 'A';
        } else if (score >= 80) {
            grade = 'B';
        } else if (score >= 70) {
            grade = 'C';
        } else {
            grade = 'F';
        }

        System.out.println(grade);

        //String은 객체이므로 문자열의 실제 값을 비교할 때 equals() 사용.
        //new String()으로 생성하면 각각 새로운 객체가 생성되어 서로 다른 메모리 주소(참조값)를 가지므로 == 비교 시 false가 될 수 있음.
        // == → 참조(같은 객체인지) 비교 / equals() → 객체가 가지고 있는 문자열 내용(값) 비교

        String a = "kimbap";
        //String b = "kimbap";
        String b = "Kimbap";

        //System.out.println(a==b); //new 선언 전 true 같은 문자열을 재사용 / new로 선언해서 비교할 경우 false
        if (a.equals(b)){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}