package homework_04;

import java.util.Scanner;

public class BirthYear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("age?");

        int age = Integer.parseInt(sc.nextLine());
        int year = 2026 - age ;

        System.out.println("year="+year);
    }
}
