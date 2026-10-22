package homework_05;

import java.util.Scanner;

public class ShortRetry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int price = 4500;
        int change = -1;

        while (change < 0) {
            System.out.println("pay?");
            int pay = Integer.parseInt(sc.nextLine());

            change = pay - price;
            if (change < 0) {
                System.out.println("short");
            }
        }

        System.out.println("change="+change);




    }
}
