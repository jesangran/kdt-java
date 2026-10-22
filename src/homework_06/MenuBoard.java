package homework_06;

import java.util.Scanner;

public class MenuBoard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] menus = new String[3];
        for (int i = 0; i < menus.length; i++){
            System.out.println("menu?");
            menus[i] = sc.nextLine();
        }

        for (int i = 0; i < menus.length; i++){
            System.out.println("menu="+menus[i]);
        }
    }
}
