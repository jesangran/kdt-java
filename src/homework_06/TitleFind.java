package homework_06;

import java.util.Scanner;

public class TitleFind {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] titles = { "closed", "exam", "holiday" };

        System.out.println("find?");
        String find = sc.nextLine();
        boolean found = false;

        for(int i = 0; i < titles.length; i++){
            if(titles[i].equals(find)){
                System.out.println("index="+i);
                found = true;
            }
        }
        if(!found){
            System.out.println("missing");
        }
    }
}
