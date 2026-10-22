package homework_02;

import java.util.Scanner;

public class OrderCard {
    public static void main(String[] args) {

        int kimbap = 4500;
        int water = 1000;
        int total = kimbap + water;

        Scanner sc = new Scanner(System.in);

        System.out.println("nick?");
        String nick = sc.nextLine();

        System.out.println("menu?");
        String menu = sc.nextLine();

        System.out.println("note?");
        String note = sc.nextLine();

        System.out.println("nick="+nick);
        System.out.println("menu="+menu);
        System.out.println("note="+note);
        System.out.println("kimbap="+kimbap);
        System.out.println("water="+water);
        System.out.println("total="+total);
        System.out.println("slip="+nick+"/"+menu+"/"+total);

    }
}
