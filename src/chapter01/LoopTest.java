package chapter01;

public class LoopTest {
    public static void main(String[] args) {
 /*        for(시작값; (끝나는)조건; 마지막 순회 처리방법) { 반복문장 }
        for (int n = 1; n < 6; n++) { //증가
            System.out.println("closed day " + n);
        }

        for (int n = 100; n > 0; n--) { //감소
            System.out.println("closed day " + n);
        }

        for(int n = 2; n <= 100; n += 2) {
            System.out.println("closed day " + n);
        }

        int n = 1;
        while (true) {
           // n <= 10, break
            if (n >= 10){
                break;
            }
            n = n + 1;
            System.out.println(n);
        }*/

        //구구단
        for (int n = 2; n < 10; n++){
            for (int i =1; i < 10; i++) {
                System.out.println(n+"*"+i+"="+n*i);
            }
        }

    }
}
