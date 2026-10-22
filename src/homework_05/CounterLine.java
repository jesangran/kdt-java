package homework_05;

import java.util.Scanner;

public class CounterLine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("menu?");
        String menu = sc.nextLine();

        int price = 0;

        if(menu.equals("tuna")){
            price = 5000;
        } else if (menu.equals("kimbap")) {
            price = 4500;
        } else {
            System.out.println("unknown");
        }

        if( price != 0 ){
            System.out.println("price="+ price);

            System.out.println("pay?");
            String pay = sc.nextLine();

            int change = Integer.parseInt(pay) - price;

            if(change <= 0){
                System.out.println("short");
            }else {
                System.out.println("change="+change);
            }
        }




    }
}
