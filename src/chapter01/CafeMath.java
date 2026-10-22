package chapter01;

public class CafeMath {
    public static void main(String[] args) {

        int won = 10000;
        int people = 3;
        int each = won / people; // 몫
        int left = won % people; // 나머지

        /*
        double 부동 소수점, 계산할때는 type은 맞춰주는게 중요하다.
        자원을 아끼려고 형을 분리해서 사용했다고 함
        요즘에는 메모리를 자동으로 할당
        변수는 대소문자를 구분, 타입명으로 변수명 사용 불가
        int(정수)의 범위를 넘어서면 long
        */

        System.out.println("each=" + each); //println 줄바꿈 o
        System.out.println("left=" + left); //print 줄바꿈x

        //int err = won / 0;

        System.out.println("Integer.MAX_VALUE >>"+Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE >>"+Integer.MIN_VALUE);

        double PI = 3.14; //고정값(약속된 값)이라서 대문자로 작성
        //변수에 final을 선언 시 값이 불변함

    }
}
