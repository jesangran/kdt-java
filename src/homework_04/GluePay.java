package homework_04;

import java.util.Scanner;

public class GluePay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("kimbap?");
        String rawKimbap = sc.nextLine();

        System.out.println("water?");
        String rawWater = sc.nextLine();

        String glued = rawKimbap + rawWater ;
        System.out.println("glued="+glued);

        int sum = Integer.parseInt(rawKimbap) + Integer.parseInt(rawWater);
        System.out.println("sum="+sum);

        //심화4.
        int packed = Integer.parseInt(rawKimbap + rawWater);
        System.out.println("packed="+packed);
    }
}
