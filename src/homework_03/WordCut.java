package homework_03;

import java.util.Scanner;

public class WordCut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("menu?");
        String word = sc.next();
        String rest = sc.nextLine();

        System.out.println("word="+word);
        System.out.println("rest="+rest);
    }
}
