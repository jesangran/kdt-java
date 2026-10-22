package homework_04;

import java.util.Scanner;

public class LunchSpend {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("subway?");
        int subway = Integer.parseInt(sc.nextLine());

        System.out.println("kimbap?");
        int kimbap = Integer.parseInt(sc.nextLine());

        int spend = subway + kimbap ;
        System.out.println("spend="+spend);
    }


}
