package homework_05;

import java.util.Scanner;

public class YouthLine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("age?");
        int age = Integer.parseInt(sc.nextLine());

        if(age <= 19) {
            System.out.println("youth!");
        }else {
            System.out.println("adult");
        }
    }
}
