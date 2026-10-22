package homework_03;

import java.util.Scanner;

public class CafePickup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("nickname?");
        String nickname = sc.nextLine();

        System.out.println("menu?");
        String menu = sc.nextLine();

        System.out.println("place?");
        String place = sc.nextLine();

        System.out.println("pickup="+nickname+" "+menu+" "+place);
    }
}
