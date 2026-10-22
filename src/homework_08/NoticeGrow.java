package homework_08;

import java.util.ArrayList;
import java.util.Scanner;

public class NoticeGrow {
    public static void main(String[] args) {
        ArrayList<String> titles = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("title?");
        titles.add(sc.nextLine());
        System.out.println("title?");
        titles.add(sc.nextLine());
        System.out.println("title?");
        titles.add(sc.nextLine());

        System.out.println("size=" + titles.size());

        for (int i = 0; i < titles.size(); i++) {
            System.out.println("saved=" + titles.get(i));
        }
    }
}
